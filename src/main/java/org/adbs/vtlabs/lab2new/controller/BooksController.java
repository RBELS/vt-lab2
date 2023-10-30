package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.model.service.Book;
import org.adbs.vtlabs.lab2new.service.BookService;

import java.io.IOException;

public class BooksController {
    private BookService bookService = BookService.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Book book;
        book = bookService.getBookById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/bookView.jsp").forward(req, resp);
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Book book;
        book = bookService.getBookById(id);
        bookService.deleteById(book.getBookId());
        resp.sendRedirect(req.getContextPath()+"/books");
    }
}
