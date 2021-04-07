package com.epam.hrsystem.controller.command;

import com.epam.hrsystem.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface that represents "Command" pattern. Used by a controller.
 *
 * @author Aleksey Klevitov
 */
public interface ActionCommand {
    /**
     * Processes a request from controller and returns the page to be redirected.
     *
     * @param request  Request object.
     * @param response Response object.
     * @return CommandResult object.
     * @throws CommandException if an exception has occurred while executing.
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
