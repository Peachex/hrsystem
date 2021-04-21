package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Action command changes user's role.
 *
 * @author Aleksey Klevitov
 */
public class ChangeUserRoleCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserService service = UserServiceImpl.getInstance();
        String userIdStr = request.getParameter(RequestParameter.USER_ID);
        String newUserRole = request.getParameter(RequestParameter.USER_ROLE);
        CommandResult result = new CommandResult(CommandName.TO_ADMIN_USER_INFO + userIdStr, CommandResult.Type.FORWARD);
        try {
            long userId = Long.parseLong(userIdStr);
            if (service.changeUserRole(userId, newUserRole)) {
                result = new CommandResult(CommandName.TO_ADMIN_USER_INFO + userIdStr, CommandResult.Type.REDIRECT);
            } else {
                request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't convert from string to long str = " + userIdStr + ": " + e);
            request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't change user role");
            throw new CommandException(e);
        }
        return result;
    }
}
