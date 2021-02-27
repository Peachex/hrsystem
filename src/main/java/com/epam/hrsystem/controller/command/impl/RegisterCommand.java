package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.Constant;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;;
import com.epam.hrsystem.controller.attribute.UrlPattern;
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
import java.util.LinkedHashMap;
import java.util.Map;

public class RegisterCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String dateOfBirth = request.getParameter(RequestParameter.DATE_OF_BIRTH);
        String phoneNumber = request.getParameter(RequestParameter.PHONE_NUMBER);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String repeatedPassword = request.getParameter(RequestParameter.REPEATED_PASSWORD);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.FIRST_NAME, firstName);
        fields.put(RequestParameter.LAST_NAME, lastName);
        fields.put(RequestParameter.DATE_OF_BIRTH, dateOfBirth);
        fields.put(RequestParameter.PHONE_NUMBER, phoneNumber);
        fields.put(RequestParameter.EMAIL, email);
        fields.put(RequestParameter.PASSWORD, password);
        fields.put(RequestParameter.REPEATED_PASSWORD, repeatedPassword);

        UserService service = UserServiceImpl.INSTANCE;
        CommandResult result;
        try {
            if (service.register(fields)) {
                result = new CommandResult(UrlPattern.LOGIN, CommandResult.Type.REDIRECT);
            } else {
                request.setAttribute(RequestParameter.FIRST_NAME, fields.get(RequestParameter.FIRST_NAME));
                request.setAttribute(RequestParameter.LAST_NAME, fields.get(RequestParameter.LAST_NAME));
                request.setAttribute(RequestParameter.DATE_OF_BIRTH, fields.get(RequestParameter.DATE_OF_BIRTH));
                request.setAttribute(RequestParameter.PHONE_NUMBER, fields.get(RequestParameter.PHONE_NUMBER));
                request.setAttribute(RequestParameter.EMAIL, fields.get(RequestParameter.EMAIL));
                request.setAttribute(RequestParameter.PASSWORD, fields.get(RequestParameter.PASSWORD));
                request.setAttribute(RequestParameter.REPEATED_PASSWORD, fields.get(RequestParameter.REPEATED_PASSWORD));
                request.setAttribute(Constant.ERROR_INPUT_DATA_ATTRIBUTE, Constant.ERROR_INPUT_DATA_MESSAGE);
                result = new CommandResult(PagePath.REGISTER, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't register user");
            throw new CommandException(e);
        }
        return result;
    }
}
