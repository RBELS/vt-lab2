package org.adbs.vtlabs.lab2new.repository;

import org.adbs.vtlabs.lab2new.model.entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserRepository {
    private static UserRepository instance;
    public static synchronized UserRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserRepository();
        }
        return instance;
    }

    private final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

    public Optional<UserEntity> getUserById(Long userId) throws SQLException {
        Connection connection = null;
        try {
           connection = connectionProvider.getConnection();
           PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_ID);
           statement.setLong(1, userId);
           ResultSet resultSet = statement.executeQuery();
           return userEntityOptionalExtractor(resultSet);
        } finally {
            connectionProvider.releaseConnection(connection);
        }
    }

    public Optional<UserEntity> findByUsernameAndHash(String username, String hash) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_USERNAME_AND_HASH);
            statement.setString(1, username);
            statement.setString(2, hash);
            ResultSet resultSet = statement.executeQuery();
            return userEntityOptionalExtractor(resultSet);
        } finally {
            connectionProvider.releaseConnection(connection);
        }
    }

    public UserEntity save(UserEntity userEntity) throws SQLException {
        Connection connection = null;
        try {
            connection = connectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userEntity.getUsername());
            statement.setString(2, userEntity.getHash());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            //get key
            rs.next();
            long pk = rs.getLong(1);
            return getUserById(pk).orElseThrow();
        } finally {
            connectionProvider.releaseConnection(connection);
        }
    }

    private Optional<UserEntity> userEntityOptionalExtractor(ResultSet resultSet) throws SQLException {
        Optional<UserEntity> userEntity = Optional.empty();
        if (resultSet.next()) {
            userEntity = Optional.of(new UserEntity()
                    .setUserId(resultSet.getLong(UserEntity.COLUMN_USER_ID))
                    .setUsername(resultSet.getString(UserEntity.COLUMN_USERNAME))
                    .setHash(resultSet.getString(UserEntity.COLUMN_HASH))
            );
        }
        return userEntity;
    }

    private List<UserEntity> userEntityListExtractor(ResultSet resultSet) throws SQLException {
        List<UserEntity> userEntityList = new ArrayList<>();
        while (resultSet.next()) {
            userEntityList.add(new UserEntity()
                    .setUserId(resultSet.getLong(UserEntity.COLUMN_USER_ID))
                    .setUsername(resultSet.getString(UserEntity.COLUMN_USERNAME))
                    .setHash(resultSet.getString(UserEntity.COLUMN_HASH))
            );
        }
        return userEntityList;
    }
}
