package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.exception.ErrorCode;
import org.adbs.vtlabs.lab2new.exception.ErrorData;
import org.adbs.vtlabs.lab2new.exception.ServiceException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "mainServlet", value = "/mainServlet")
public class MainServlet extends HttpServlet {
    private static final HomeController homeController = new HomeController();
    private static final LoginController loginController = new LoginController();
    private static final LogoutController logoutController = new LogoutController();
    private static final RegisterController registerController = new RegisterController();
    private static final CreateBookController createBookController = new CreateBookController();
    private static final BooksController booksController = new BooksController();

    private static final Map<String, ControllerEndpoint> getEndpointsMap;
    private static final Map<String, ControllerEndpoint> postEndpointsMap;
    private static final Map<String, ControllerEndpoint> deleteEndpointsMap;

    static {
        getEndpointsMap = new HashMap<>();
        getEndpointsMap.put("home", homeController::doGet);
        getEndpointsMap.put("login", loginController::doGet);
        getEndpointsMap.put("logout", logoutController::doGet);
        getEndpointsMap.put("register", registerController::doGet);
        getEndpointsMap.put("create", createBookController::doGet);
        getEndpointsMap.put("book", booksController::doGet);

        postEndpointsMap = new HashMap<>();
        postEndpointsMap.put("login", loginController::doPost);
        postEndpointsMap.put("register", registerController::doPost);
        postEndpointsMap.put("create", createBookController::doPost);

        deleteEndpointsMap = new HashMap<>();
        deleteEndpointsMap.put("book", booksController::doDelete);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req, resp, getEndpointsMap);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req, resp, postEndpointsMap);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req, resp, deleteEndpointsMap);
    }

    private void dispatchRequest(HttpServletRequest req, HttpServletResponse resp, Map<String, ControllerEndpoint> endpointMap) throws ServletException, IOException {
        try {
            ControllerEndpoint endpoint = endpointMap.get(extractCommand(req.getRequestURI()));
            if (Objects.nonNull(endpoint)) {
                endpoint.process(req, resp);
            } else {
                dispatchError(req, resp, ErrorCode.PAGE_NOT_FOUND);
            }
        } catch (ServiceException e) {
            dispatchError(req, resp, e.getErrorCode());
        } catch (Exception e) {
            dispatchError(req, resp, ErrorCode.INTERNAL_ERROR);
        }
    }

    private void dispatchError(HttpServletRequest req, HttpServletResponse resp, ErrorCode errorCode) throws ServletException, IOException {
        ErrorData errorData = ErrorCode.ERROR_DATA.get(errorCode);
        resp.setStatus(errorData.getCode());
        req.setAttribute("error", errorData);
        req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }

    private String extractCommand(String fullUrl) {
        String[] uriComponents = fullUrl.split("/");
        return uriComponents.length == 0 ? "home" : uriComponents[1];
    }
}
