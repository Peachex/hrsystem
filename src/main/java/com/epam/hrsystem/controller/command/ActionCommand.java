package com.epam.hrsystem.controller.command;

import com.epam.hrsystem.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    //todo delete response
    CommandResult execute(HttpServletRequest request) throws CommandException;
}
