package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.UrlPattern;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.impl.UserServiceImpl;
import com.epam.hrsystem.validator.BaseValidator;
import com.epam.hrsystem.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class RegisterCommand implements ActionCommand {
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
            if (service.signUp(fields)) {
                result = new CommandResult(UrlPattern.VACANCY, CommandResult.Type.REDIRECT);
            } else {
                if (!UserValidator.isNameValid(firstName)) {
                    request.setAttribute("errorFirstName", "First name isn't valid");
                }
                if (!UserValidator.isNameValid(lastName)) {
                    request.setAttribute("errorLastName", "Last name isn't valid");
                }
                if (!BaseValidator.isDateFormatValid(dateOfBirth)) {
                    request.setAttribute("errorDateOfBirth", "Date of birth isn't valid");
                }
                if (!UserValidator.isPhoneNumberValid(phoneNumber)) {
                    request.setAttribute("errorPhoneNumber", "Phone number isn't valid");
                }
                if (!UserValidator.isEmailValid(email)) {
                    request.setAttribute("errorEmail", "Email isn't valid");
                }
                if (!UserValidator.isPasswordValid(password)) {
                    request.setAttribute("errorPassword", "Password isn't valid");
                }
                if (!UserValidator.isPasswordValid(password, repeatedPassword)) {
                    request.setAttribute("errorRepeatedPassword", "Repeated password isn't valid");
                }
                result = new CommandResult(UrlPattern.REGISTER, CommandResult.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
