package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.service.BookService;
import org.adbs.vtlabs.lab2new.util.CookieExtractor;

import java.io.IOException;
import java.util.Map;

public class HomeController {
    private BookService bookService = BookService.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", bookService.getAllBooks());
        Map<String, String> langMap = LangController.langMap.get(CookieExtractor.extractLang(req.getCookies()).orElse("en"));
        req.setAttribute("lang", langMap);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
