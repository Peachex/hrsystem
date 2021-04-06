package com.epam.hrsystem.exception;

/**
 * An exception that provides information on errors thrown by Service objects.
 *
 * @author Aleksey Klevitov
 */
public class ServiceException extends Exception {
    /**
     * Constructs a ServiceException object with a given cause.
     *
     * @param cause Throwable object of the given cause.
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a ServiceException object with a given message.
     *
     * @param message String object of the given message.
     */
    public ServiceException(String message) {
        super(message);
    }
}
