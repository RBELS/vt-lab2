package org.adbs.vtlabs.lab2new.service;

import org.adbs.vtlabs.lab2new.model.service.Book;
import org.adbs.vtlabs.lab2new.storage.BookStorage;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class BookService {
    private static volatile BookService instance;
    public static synchronized BookService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BookService();
        }
        return instance;
    }

    private final BookStorage bookStorage = BookStorage.getInstance();

    public List<Book> getAllBooks() throws SQLException {
        return bookStorage.findAll();
    }

    public Book addNewBook(Book book) throws SQLException {
        return bookStorage.save(book);
    }

    public Book getBookById(Long bookId) throws SQLException {
        return bookStorage.findById(bookId)
                .orElseThrow();
    }

    public boolean deleteById(Long bookId) throws SQLException {
        return bookStorage.deleteById(bookId);
    }
}
