package org.adbs.vtlabs.lab2new.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Невалидный токен.");
    }
}
