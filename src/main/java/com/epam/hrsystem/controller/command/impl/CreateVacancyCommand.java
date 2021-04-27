package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.model.service.impl.VacancyServiceImpl;
import com.epam.hrsystem.validator.VacancyValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Action command creates vacancy.
 *
 * @author Aleksey Klevitov
 */
public class CreateVacancyCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        long employeeId = (long) session.getAttribute(SessionAttribute.USER_ID);
        String position = request.getParameter(RequestParameter.POSITION);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String country = request.getParameter(RequestParameter.COUNTRY);
        String city = request.getParameter(RequestParameter.CITY);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.POSITION, position);
        fields.put(RequestParameter.DESCRIPTION, description);
        fields.put(RequestParameter.COUNTRY, country);
        fields.put(RequestParameter.CITY, city);

        VacancyService service = VacancyServiceImpl.getInstance();
        CommandResult result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES, CommandResult.Type.REDIRECT);
        try {
            if (service.createVacancy(fields, employeeId)) {
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
            } else {
                if (VacancyValidator.isVacancyFormValid(fields)) {
                    request.setAttribute(JspAttribute.ERROR_DUPLICATE_ATTRIBUTE, JspAttribute.ERROR_VACANCY_DUPLICATE_MESSAGE);
                } else {
                    request.setAttribute(RequestParameter.POSITION, fields.get(RequestParameter.POSITION));
                    request.setAttribute(RequestParameter.DESCRIPTION, fields.get(RequestParameter.DESCRIPTION));
                    request.setAttribute(RequestParameter.COUNTRY, fields.get(RequestParameter.COUNTRY));
                    request.setAttribute(RequestParameter.CITY, fields.get(RequestParameter.CITY));
                    request.setAttribute(JspAttribute.ERROR_VACANCY_CREATION_ATTRIBUTE, JspAttribute.ERROR_VACANCY_CREATION_MESSAGE);
                }
                result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't create vacancy");
            throw new CommandException(e);
        }
        return result;
    }
}
