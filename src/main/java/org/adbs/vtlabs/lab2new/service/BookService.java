package org.adbs.vtlabs.lab2new.service;

import org.adbs.vtlabs.lab2new.exception.ErrorCode;
import org.adbs.vtlabs.lab2new.exception.ServiceException;
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

    public List<Book> getAllBooks() {
        try {
            return bookStorage.findAll();
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR);
        }
    }

    public Book addNewBook(Book book) {
        try {
            return bookStorage.save(book);
        } catch (SQLException e) {
            throw new ServiceException(ErrorCode.BAD_REQUEST);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR);
        }
    }

    public Book getBookById(Long bookId) {
        try {
            return bookStorage.findById(bookId)
                    .orElseThrow();
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.BOOK_NOT_FOUND);
        }
    }

    public boolean deleteById(Long bookId) {
        try {
            return bookStorage.deleteById(bookId);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.BOOK_NOT_FOUND);
        }
    }
}
