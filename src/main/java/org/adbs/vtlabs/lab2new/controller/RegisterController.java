package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.model.service.User;
import org.adbs.vtlabs.lab2new.service.AuthorityService;
import org.adbs.vtlabs.lab2new.service.UserService;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class RegisterController {
    private UserService userService = UserService.getInstance();
    private AuthorityService authorityService = AuthorityService.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        if (!validateRequestParams(params)) {
            resp.setStatus(400);
            return;
        }
        User user = userService.registerUser(params.get("username")[0], params.get("password")[0]);
        String jwt = authorityService.generateUserJwt(user);
        resp.addCookie(new Cookie("auth-token", jwt));
        resp.sendRedirect(req.getContextPath()+"/");
    }

    private boolean validateRequestParams(Map<String, String[]> params) {
        return Objects.nonNull(params.get("username"))
                && Objects.nonNull(params.get("password"));
    }
}
