package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.model.service.Book;
import org.adbs.vtlabs.lab2new.service.BookService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "booksServlet", value = "/book")
public class BooksServlet extends HttpServlet {
    private BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Book book;
        try {
            book = bookService.getBookById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("book", book);
        req.getRequestDispatcher("/bookView.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Book book;
        try {
            book = bookService.getBookById(id);
            bookService.deleteById(book.getBookId());
            resp.sendRedirect(req.getContextPath()+"/books");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
