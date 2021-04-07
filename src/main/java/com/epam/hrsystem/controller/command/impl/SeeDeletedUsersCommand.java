package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Action command shows deleted users.
 *
 * @author Aleksey Klevitov
 */
public class SeeDeletedUsersCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserService service = ServiceHolder.HOLDER.getUserService();
        CommandResult result = new CommandResult(PagePath.ADMIN_USER_LIST, CommandResult.Type.FORWARD);
        try {
            List<User> users = service.findBlockedUsers();
            if (users.size() > 0) {
                request.setAttribute(RequestParameter.USERS, users);
            } else {
                request.setAttribute(JspAttribute.NO_USERS_ATTRIBUTE, JspAttribute.NO_USERS_MESSAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
