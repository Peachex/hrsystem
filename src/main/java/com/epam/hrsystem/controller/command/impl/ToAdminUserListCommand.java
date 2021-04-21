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
import com.epam.hrsystem.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Action command redirects to users list page.
 *
 * @author Aleksey Klevitov
 */
public class ToAdminUserListCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(RequestParameter.REPORT_ID);
        UserService service = UserServiceImpl.getInstance();
        CommandResult result = new CommandResult(PagePath.ADMIN_USER_LIST, CommandResult.Type.FORWARD);
        try {
            List<User> users = service.findAllUsers();
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
