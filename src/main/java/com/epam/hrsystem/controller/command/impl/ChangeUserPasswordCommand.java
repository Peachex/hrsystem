package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.impl.UserServiceImpl;
import com.epam.hrsystem.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Action command changes user's password.
 *
 * @author Aleksey Klevitov
 */
public class ChangeUserPasswordCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String currentPassword = request.getParameter(RequestParameter.CURRENT_PASSWORD);
        String newPassword = request.getParameter(RequestParameter.NEW_PASSWORD);
        String repeatedNewPassword = request.getParameter(RequestParameter.REPEATED_NEW_PASSWORD);

        Map<String, String> fields = new HashMap<>();
        fields.put(RequestParameter.NEW_PASSWORD, newPassword);
        fields.put(RequestParameter.REPEATED_NEW_PASSWORD, repeatedNewPassword);

        CommandResult result = new CommandResult(CommandName.TO_USER_PROFILE, CommandResult.Type.FORWARD);
        try {
            Optional<User> userOptional = service.login(user.getEmail(), currentPassword);
            if (userOptional.isPresent()) {
                if (service.changePassword(user.getId(), fields)) {
                    result = new CommandResult(CommandName.TO_USER_PROFILE, CommandResult.Type.REDIRECT);
                } else {
                    if (!UserValidator.isChangePasswordFormValid(fields)) {
                        request.setAttribute(RequestParameter.CURRENT_PASSWORD, currentPassword);
                        request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                    }
                }
            } else {
                request.setAttribute(RequestParameter.NEW_PASSWORD, newPassword);
                request.setAttribute(RequestParameter.REPEATED_NEW_PASSWORD, repeatedNewPassword);
                request.setAttribute(JspAttribute.ERROR_INVALID_CURRENT_PASSWORD_ATTRIBUTE, JspAttribute.ERROR_INVALID_CURRENT_PASSWORD_MESSAGE);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't change user password");
            throw new CommandException(e);
        }
        return result;
    }
}
