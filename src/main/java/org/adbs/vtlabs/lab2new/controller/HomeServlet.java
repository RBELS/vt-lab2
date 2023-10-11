package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.model.service.Book;
import org.adbs.vtlabs.lab2new.service.BookService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "homeServlet", value = "/")
public class HomeServlet extends HttpServlet {
    private BookService bookService = BookService.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books;
        try {
            books = bookService.getAllBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("books", books);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
