package com.epam.hrsystem.exception;

public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
