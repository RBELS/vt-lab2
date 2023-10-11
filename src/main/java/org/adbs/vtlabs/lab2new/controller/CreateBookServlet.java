package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.model.service.Book;
import org.adbs.vtlabs.lab2new.service.BookService;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "createBookServlet", value = "/create")
@MultipartConfig
public class CreateBookServlet extends HttpServlet {
    private BookService bookService = BookService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            bookService.addNewBook(new Book()
                    .setName(req.getParameter("name"))
                    .setAuthor(req.getParameter("author"))
                    .setPrice(new BigDecimal(req.getParameter("price")))
                    .setDescription(req.getParameter("description"))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/create.jsp").forward(req, resp);
    }
}
