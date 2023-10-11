package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.model.service.User;
import org.adbs.vtlabs.lab2new.service.AuthorityService;
import org.adbs.vtlabs.lab2new.service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    private AuthorityService authorityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = UserService.getInstance();
        authorityService = AuthorityService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    private boolean validateRequestParams(Map<String, String[]> params) {
        return Objects.nonNull(params.get("username"))
                && Objects.nonNull(params.get("password"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        if (!validateRequestParams(params)) {
            resp.setStatus(400);
            return;
        }
        try {
            User user = userService.loginUser(params.get("username")[0], params.get("password")[0]);
            String jwt = authorityService.generateUserJwt(user);
            resp.addCookie(new Cookie("auth-token", jwt));
            resp.sendRedirect(req.getContextPath()+"/home");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
