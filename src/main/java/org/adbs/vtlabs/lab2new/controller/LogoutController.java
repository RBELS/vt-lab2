package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

public class LogoutController {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("auth-token"))
                .findFirst()
                .ifPresent(cookie -> {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                });
        resp.sendRedirect(req.getContextPath()+"/home");
    }
}
