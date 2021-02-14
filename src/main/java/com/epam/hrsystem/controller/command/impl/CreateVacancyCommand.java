package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.UrlPattern;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.model.service.impl.VacancyServiceImpl;;
import com.epam.hrsystem.validator.VacancyValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateVacancyCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String position = request.getParameter(RequestParameter.POSITION);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String country = request.getParameter(RequestParameter.COUNTRY);
        String city = request.getParameter(RequestParameter.CITY);
        HttpSession session = request.getSession();
        User employee = (User) session.getAttribute(SessionAttribute.USER);
        long employeeId = employee.getId();

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.POSITION, position);
        fields.put(RequestParameter.DESCRIPTION, description);
        fields.put(RequestParameter.COUNTRY, country);
        fields.put(RequestParameter.CITY, city);

        VacancyService service = VacancyServiceImpl.INSTANCE;
        CommandResult result = new CommandResult("to_vacancy.do", CommandResult.Type.FORWARD);
        try {
            //fixme add errorFirstName and other strings to JspAttribute
            if (!service.createVacancy(fields, employeeId)) {
                if (!VacancyValidator.isPositionValid(position)) {
                    request.setAttribute("errorPosition", "Position isn't valid");
                }
                if (!VacancyValidator.isDescriptionValid(description)) {
                    request.setAttribute("errorDescription", "Description isn't valid");
                }
                if (!VacancyValidator.isCountryValid(country)) {
                    request.setAttribute("errorCountry", "Country isn't valid");
                }
                if (!VacancyValidator.isCityValid(city)) {
                    request.setAttribute("errorCity", "City isn't valid");
                }
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}

