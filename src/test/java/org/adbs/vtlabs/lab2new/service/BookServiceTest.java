package org.adbs.vtlabs.lab2new.service;

import lombok.SneakyThrows;
import org.adbs.vtlabs.lab2new.repository.ConnectionProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;

class BookServiceTest {
    private static BookService bookService;

    @BeforeAll
    @SneakyThrows
    static void beforeAll() {
//        ConnectionProvider.CONFIG_PATH = "/application-test.properties";
//        try (Connection conn = ConnectionProvider.getInstance().getConnection()) {
//            String cleanSql = new String(BookServiceTest.class.getResourceAsStream("/sql/clean.sql").readAllBytes(), StandardCharsets.UTF_8);
//            conn.prepareStatement(cleanSql).execute();
//        }
//        bookService = BookService.getInstance();
    }

    @Test
    void getAllBooks() {
//        bookService.getAllBooks();
    }

    @Test
    void addNewBook() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void deleteById() {
    }
}