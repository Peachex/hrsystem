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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Action command edits user profile.
 *
 * @author Aleksey Klevitov
 */
public class EditUserProfileCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute(SessionAttribute.USER_ID);
        String newFirstName = request.getParameter(RequestParameter.FIRST_NAME);
        String newLastName = request.getParameter(RequestParameter.LAST_NAME);
        String newDateOfBirth = request.getParameter(RequestParameter.DATE_OF_BIRTH);
        String newPhoneNumber = request.getParameter(RequestParameter.PHONE_NUMBER);
        String newEmail = request.getParameter(RequestParameter.EMAIL);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.FIRST_NAME, newFirstName);
        fields.put(RequestParameter.LAST_NAME, newLastName);
        fields.put(RequestParameter.DATE_OF_BIRTH, newDateOfBirth);
        fields.put(RequestParameter.PHONE_NUMBER, newPhoneNumber);
        fields.put(RequestParameter.EMAIL, newEmail);

        UserService service = UserServiceImpl.getInstance();
        CommandResult result;
        try {
            Optional<User> userOptional = service.updateProfile(userId, fields);
            if (userOptional.isPresent()) {
                session.setAttribute(SessionAttribute.USER, userOptional.get());
                result = new CommandResult(CommandName.TO_USER_PROFILE, CommandResult.Type.REDIRECT);
            } else {
                if (!service.isEmailAvailable(newEmail)) {
                    request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.EMAIL_AVAILABLE_ERROR_MESSAGE);
                } else {
                    request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                }
                result = new CommandResult(CommandName.TO_USER_PROFILE, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't edit user info");
            throw new CommandException(e);
        }
        return result;
    }
}
