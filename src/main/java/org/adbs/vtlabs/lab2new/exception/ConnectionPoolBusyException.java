package org.adbs.vtlabs.lab2new.exception;

public class ConnectionPoolBusyException extends RuntimeException {
    public ConnectionPoolBusyException() {
        super("Нет доступных соединений в Connection Pool.");
    }
}
