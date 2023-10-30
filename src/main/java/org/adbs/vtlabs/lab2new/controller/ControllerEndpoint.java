package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@FunctionalInterface
public interface ControllerEndpoint {
    void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
