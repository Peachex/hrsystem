package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.ServletAttribute;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Action command logs out from the user account.
 *
 * @author Aleksey Klevitov
 */
public class LogoutCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.USER_ID);
        session.removeAttribute(SessionAttribute.USER);
        session.setAttribute(SessionAttribute.CURRENT_ROLE, UserRole.GUEST);
        return (new CommandResult(ServletAttribute.HOME_URL_PATTERN, CommandResult.Type.REDIRECT));
    }
}
