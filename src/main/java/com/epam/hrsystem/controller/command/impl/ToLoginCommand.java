package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.UrlPattern;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class ToLoginCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        CommandResult result = new CommandResult(UrlPattern.LOGIN, CommandResult.Type.REDIRECT);
        return result;
    }
}
