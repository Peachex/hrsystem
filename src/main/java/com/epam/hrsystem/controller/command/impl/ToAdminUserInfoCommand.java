package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Action command redirects to user info page.
 *
 * @author Aleksey Klevitov
 */
public class ToAdminUserInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String userIdStr = request.getParameter(RequestParameter.USER_ID);
        UserService service = UserServiceImpl.getInstance();
        CommandResult result = new CommandResult(CommandName.TO_ADMIN_USER_LIST, CommandResult.Type.FORWARD);
        try {
            Optional<User> userOptional = service.findUserById(Long.parseLong(userIdStr));
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                request.setAttribute(RequestParameter.USER, user);
                result = new CommandResult(PagePath.ADMIN_USER_INFO, CommandResult.Type.FORWARD);
            } else {
                request.setAttribute(JspAttribute.NO_USER_ATTRIBUTE, JspAttribute.NO_USER_MESSAGE);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't convert from string to long str = " + userIdStr + ": " + e);
            request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return result;
    }
}
