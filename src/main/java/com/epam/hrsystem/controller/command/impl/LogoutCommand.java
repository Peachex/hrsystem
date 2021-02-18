package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.UrlPattern;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.USER);
        session.setAttribute(SessionAttribute.CURRENT_ROLE, UserRole.GUEST);
        CommandResult result = new CommandResult(UrlPattern.HOME, CommandResult.Type.REDIRECT);
        return result;
    }
}
