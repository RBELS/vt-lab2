package org.adbs.vtlabs.lab2new.repository;

import org.adbs.vtlabs.lab2new.model.entity.BookEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BookRepository {
    private static BookRepository instance;
    public static synchronized BookRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BookRepository();
        }
        return instance;
    }

    private final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

    public List<BookEntity> findAll() throws SQLException {
        Connection connection = null;
        try {
            connection = connectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_BOOKS);
            return bookEntityListExtractor(statement.executeQuery());
        } finally {
            connectionProvider.releaseConnection(connection);
        }
    }

    public Optional<BookEntity> findById(Long bookId) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_BOOK_BY_ID);
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            return bookEntityOptionalExtractor(resultSet);
        } finally {
            connectionProvider.releaseConnection(connection);
        }
    }

    public boolean deleteById(Long bookId) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_BOOK);
            statement.setLong(1, bookId);
            return statement.executeUpdate() != 0;
        } finally {
            connectionProvider.releaseConnection(connection);
        }
    }

    public BookEntity save(BookEntity bookEntity) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.SAVE_BOOK, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, bookEntity.getName());
            statement.setString(2, bookEntity.getAuthor());
            statement.setBigDecimal(3, bookEntity.getPrice());
            statement.setString(4, bookEntity.getDescription());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            //get key
            rs.next();
            long pk = rs.getLong(1);
            return findById(pk).orElseThrow();
        } finally {
            connectionProvider.releaseConnection(connection);
        }
    }

    public BookEntity saveForUpdate(BookEntity bookEntity) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_BOOK);
            statement.setString(1, bookEntity.getName());
            statement.setString(2, bookEntity.getAuthor());
            statement.setBigDecimal(3, bookEntity.getPrice());
            statement.setLong(4, bookEntity.getBookId());
            statement.setString(5, bookEntity.getDescription());
            statement.execute();
            return findById(bookEntity.getBookId()).orElseThrow();
        } finally {
            connectionProvider.releaseConnection(connection);
        }
    }

    private Optional<BookEntity> bookEntityOptionalExtractor(ResultSet resultSet) throws SQLException {
        Optional<BookEntity> bookEntity = Optional.empty();
        if (resultSet.next()) {
            bookEntity = Optional.of(new BookEntity()
                    .setBookId(resultSet.getLong(BookEntity.COLUMN_BOOK_ID))
                    .setName(resultSet.getString(BookEntity.COLUMN_NAME))
                    .setAuthor(resultSet.getString(BookEntity.COLUMN_AUTHOR))
                    .setPrice(resultSet.getBigDecimal(BookEntity.COLUMN_PRICE))
                    .setDescription(resultSet.getString(BookEntity.COLUMN_DESCRIPTION))
            );
        }
        return bookEntity;
    }

    private List<BookEntity> bookEntityListExtractor(ResultSet resultSet) throws SQLException {
        List<BookEntity> bookEntityList = new ArrayList<>();
        while (resultSet.next()) {
            bookEntityList.add(new BookEntity()
                    .setBookId(resultSet.getLong(BookEntity.COLUMN_BOOK_ID))
                    .setName(resultSet.getString(BookEntity.COLUMN_NAME))
                    .setAuthor(resultSet.getString(BookEntity.COLUMN_AUTHOR))
                    .setPrice(resultSet.getBigDecimal(BookEntity.COLUMN_PRICE))
                    .setDescription(resultSet.getString(BookEntity.COLUMN_DESCRIPTION))
            );
        }
        return bookEntityList;
    }
}
