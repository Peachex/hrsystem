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
import java.util.List;

/**
 * Action command finds users by key word.
 *
 * @author Aleksey Klevitov
 */
public class FindUsersByKeyWordCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserService service = UserServiceImpl.getInstance();
        try {
            String keyWord = request.getParameter(RequestParameter.KEY_WORD);
            List<User> users = service.findUsersByKeyWord(keyWord);
            if (users.size() == 0) {
                request.setAttribute(JspAttribute.NO_USERS_ATTRIBUTE, JspAttribute.NO_USERS_BY_REQUEST_MESSAGE);
            }
            request.setAttribute(RequestParameter.USERS, users);
            request.setAttribute(RequestParameter.KEY_WORD, keyWord);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return (new CommandResult(PagePath.ADMIN_USER_LIST, CommandResult.Type.FORWARD));
    }
}
