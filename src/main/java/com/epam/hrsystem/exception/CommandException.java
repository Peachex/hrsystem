package com.epam.hrsystem.exception;

public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
