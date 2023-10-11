package org.adbs.vtlabs.lab2new.storage;

import org.adbs.vtlabs.lab2new.components.ModelMapperProvider;
import org.adbs.vtlabs.lab2new.model.entity.BookEntity;
import org.adbs.vtlabs.lab2new.model.service.Book;
import org.adbs.vtlabs.lab2new.repository.BookRepository;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BookStorage {
    private static volatile BookStorage instance;
    public static synchronized BookStorage getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BookStorage();
        }
        return instance;
    }

    private final BookRepository bookRepository = BookRepository.getInstance();
    private final ModelMapper modelMapper = ModelMapperProvider.getInstance();

    public List<Book> findAll() throws SQLException {
        return bookRepository.findAll()
                .stream().map(bookEntity -> modelMapper.map(bookEntity, Book.class))
                .toList();
    }

    public Optional<Book> findById(Long bookId) throws SQLException {
        return bookRepository.findById(bookId)
                .map(bookEntity -> modelMapper.map(bookEntity, Book.class));
    }

    public Book save(Book book) throws SQLException {
        return modelMapper.map(
                bookRepository.save(modelMapper.map(book, BookEntity.class)),
                Book.class
        );
    }

    public Book saveForUpdate(Book book) throws SQLException {
        return modelMapper.map(
                bookRepository.saveForUpdate(modelMapper.map(book, BookEntity.class)),
                Book.class
        );
    }

    public boolean deleteById(Long bookId) throws SQLException {
        return bookRepository.deleteById(bookId);
    }
}
