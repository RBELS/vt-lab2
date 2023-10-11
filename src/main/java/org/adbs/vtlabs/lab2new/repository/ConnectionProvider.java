package org.adbs.vtlabs.lab2new.repository;

import lombok.SneakyThrows;
import org.adbs.vtlabs.lab2new.exception.ConnectionPoolBusyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionProvider {
    private static final Integer poolSize = 10;
    private static ConnectionProvider instance;

    private final Map<Connection, Boolean> connectionPool = new ConcurrentHashMap<>();
    private final Logger log = LoggerFactory.getLogger(ConnectionProvider.class.getName());

    @SneakyThrows
    public static synchronized ConnectionProvider getInstance() {
        Class.forName("org.postgresql.Driver");
        if (Objects.isNull(instance)) {
            instance = new ConnectionProvider();
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {
        Optional<Connection> freePoolConnection = connectionPool.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .findAny();
        if (freePoolConnection.isPresent()) {
            return freePoolConnection.get();
        } else if (connectionPool.size() < poolSize) {
            Connection newConnection = DriverManager.getConnection("jdbc:postgresql://localhost:32768/EShopDB", "postgres", "postgrespw");
            connectionPool.put(newConnection, false);
            return newConnection;
        } else {
            log.error("Нет доступных соединений в Connection Pool.");
            throw new ConnectionPoolBusyException();
        }
    }

    public void releaseConnection(Connection connection) {
        if (Objects.nonNull(connection)) {
            connectionPool.put(connection, true);
        }
    }
}
