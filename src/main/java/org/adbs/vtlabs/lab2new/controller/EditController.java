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

public class EditController {
    private BookService bookService = BookService.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Book book = bookService.getBookById(id);
        req.setAttribute("book", book);
        Map<String, String> langMap = LangController.langMap.get(CookieExtractor.extractLang(req.getCookies()).orElse("en"));
        req.setAttribute("lang", langMap);
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService.updateBook(new Book()
                .setBookId(Long.valueOf(req.getParameter("id")))
                .setName(req.getParameter("name"))
                .setAuthor(req.getParameter("author"))
                .setPrice(new BigDecimal(req.getParameter("price")))
                .setDescription(req.getParameter("description"))
        );
        resp.sendRedirect("/");
    }
}
