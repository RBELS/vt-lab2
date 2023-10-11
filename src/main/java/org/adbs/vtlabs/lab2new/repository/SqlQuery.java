package org.adbs.vtlabs.lab2new.repository;

public class SqlQuery {
    public static final String SELECT_USER_BY_ID = "SELECT * FROM \"USER\" as u WHERE u.\"USER_ID\" = ?";
    public static final String SELECT_USER_BY_USERNAME_AND_HASH = "SELECT * FROM \"USER\" as u WHERE u.\"USERNAME\" = ? AND u.\"HASH\" = ?";
    public static final String SAVE_USER = "INSERT INTO \"USER\" (\"USERNAME\", \"HASH\") VALUES (?, ?)";

    public static final String SELECT_ALL_BOOKS = "SELECT * FROM \"BOOK\"";
    public static final String SELECT_BOOK_BY_ID = "SELECT * FROM \"BOOK\" WHERE \"BOOK_ID\" = ?";
    public static final String SAVE_BOOK = "INSERT INTO \"BOOK\" (\"NAME\", \"AUTHOR\", \"PRICE\", \"description\") VALUES (?, ?, ?, ?)";
    public static final String DELETE_BOOK = "DELETE FROM \"BOOK\" WHERE \"BOOK_ID\" = ?";
    public static final String UPDATE_BOOK = "UPDATE \"BOOK\" SET \"NAME\" = ?, \"AUTHOR\" = ?, \"PRICE\" = ? WHERE \"BOOK_ID\" = ?";
}
