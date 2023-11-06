package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.model.service.Book;
import org.adbs.vtlabs.lab2new.service.BookService;
import org.adbs.vtlabs.lab2new.util.CookieExtractor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class CreateBookController {
    private BookService bookService = BookService.getInstance();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService.addNewBook(new Book()
                .setName(req.getParameter("name"))
                .setAuthor(req.getParameter("author"))
                .setPrice(new BigDecimal(req.getParameter("price")))
                .setDescription(req.getParameter("description"))
        );
        resp.sendRedirect("/");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> langMap = LangController.langMap.get(CookieExtractor.extractLang(req.getCookies()).orElse("en"));
        req.setAttribute("lang", langMap);
        req.getRequestDispatcher("/create.jsp").forward(req, resp);
    }
}
