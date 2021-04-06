package com.epam.hrsystem.exception;

/**
 * An exception that provides information on errors occurred while processing a command.
 *
 * @author Aleksey Klevitov
 */
public class CommandException extends Exception {
    /**
     * Constructs a CommandException object with a given cause.
     *
     * @param cause Throwable object of the given cause.
     */
    public CommandException(Throwable cause) {
        super(cause);
    }
}
