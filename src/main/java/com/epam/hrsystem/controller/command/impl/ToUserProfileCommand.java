package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Action command redirects to user profile page.
 *
 * @author Aleksey Klevitov
 */
public class ToUserProfileCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        request.setAttribute(RequestParameter.FIRST_NAME, user.getFirstName());
        request.setAttribute(RequestParameter.LAST_NAME, user.getLastName());
        request.setAttribute(RequestParameter.DATE_OF_BIRTH, user.getDateOfBirth());
        request.setAttribute(RequestParameter.PHONE_NUMBER, user.getPhoneNumber());
        request.setAttribute(RequestParameter.EMAIL, user.getEmail());
        CommandResult result = new CommandResult(PagePath.USER_PROFILE, CommandResult.Type.FORWARD);
        return result;
    }
}
