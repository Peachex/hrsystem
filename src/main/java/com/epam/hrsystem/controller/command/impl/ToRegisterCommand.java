package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class ToRegisterCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        CommandResult result = new CommandResult(PagePath.REGISTER, CommandResult.Type.FORWARD);
        return result;
    }
}
