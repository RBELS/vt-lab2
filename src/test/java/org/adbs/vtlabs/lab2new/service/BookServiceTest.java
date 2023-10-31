package org.adbs.vtlabs.lab2new.service;

import lombok.SneakyThrows;
import org.adbs.vtlabs.lab2new.model.service.Book;
import org.adbs.vtlabs.lab2new.repository.ConnectionProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.List;

class BookServiceTest {
    private static BookService bookService;

    @BeforeEach
    @SneakyThrows
    void beforeEach() {
        ConnectionProvider.CONFIG_PATH = "/application-test.properties";
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
        Connection conn = null;
        try {
            conn = connectionProvider.getConnection();
            String cleanSql = new String(BookServiceTest.class.getResourceAsStream("/sql/clean.sql").readAllBytes(), StandardCharsets.UTF_8);
            conn.prepareStatement(cleanSql).executeUpdate();
            bookService = BookService.getInstance();
        } finally {
            connectionProvider.releaseConnection(conn);
        }
    }

    @Test
    void getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        Assertions.assertEquals(5, books.size());
        Assertions.assertAll(
                () -> Assertions.assertEquals("Book 1", books.get(0).getName()),
                () -> Assertions.assertEquals("Author 1", books.get(0).getAuthor()),
                () -> Assertions.assertEquals("Description 1", books.get(0).getDescription()),
                () -> Assertions.assertEquals(new BigDecimal("10.00"), books.get(0).getPrice())
        );
    }

    @Test
    void addNewBook() {
        Book book = bookService.addNewBook(new Book()
                .setName("Book 6")
                .setAuthor("Author 6")
                .setDescription("Description 6")
                .setPrice(new BigDecimal("60.00"))
        );
    }

    @Test
    void getBookById() {
        List<Book> books = bookService.getAllBooks();
        Book book = bookService.getBookById(books.get(0).getBookId());
        Assertions.assertAll(
                () -> Assertions.assertEquals(books.get(0).getBookId(), book.getBookId()),
                () -> Assertions.assertEquals(books.get(0).getName(), book.getName()),
                () -> Assertions.assertEquals(books.get(0).getAuthor(), book.getAuthor()),
                () -> Assertions.assertEquals(books.get(0).getDescription(), book.getDescription()),
                () -> Assertions.assertEquals(books.get(0).getPrice(), book.getPrice())
        );
    }

    @Test
    void deleteById() {
        bookService.deleteById(3L);
        Assertions.assertTrue(bookService.getAllBooks().stream()
                .map(Book::getBookId)
                .filter(bookId -> bookId == 3L)
                .findFirst()
                .isEmpty()
        );
    }
}