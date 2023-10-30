package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.service.BookService;

import java.io.IOException;

public class HomeController {
    private BookService bookService = BookService.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", bookService.getAllBooks());
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
