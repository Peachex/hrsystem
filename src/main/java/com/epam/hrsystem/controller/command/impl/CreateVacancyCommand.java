package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.Constant;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.model.service.impl.VacancyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateVacancyCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String position = request.getParameter(RequestParameter.POSITION);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String country = request.getParameter(RequestParameter.COUNTRY);
        String city = request.getParameter(RequestParameter.CITY);

        HttpSession session = request.getSession();
        long employeeId = (long) session.getAttribute(SessionAttribute.USER_ID);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.POSITION, position);
        fields.put(RequestParameter.DESCRIPTION, description);
        fields.put(RequestParameter.COUNTRY, country);
        fields.put(RequestParameter.CITY, city);

        VacancyService service = VacancyServiceImpl.INSTANCE;
        CommandResult result = new CommandResult( CommandName.TO_EMPLOYEE_VACANCIES + employeeId, CommandResult.Type.REDIRECT);
        try {
            if (!service.createVacancy(fields, employeeId)) {
                request.setAttribute(RequestParameter.POSITION, fields.get(RequestParameter.POSITION));
                request.setAttribute(RequestParameter.COUNTRY, fields.get(RequestParameter.COUNTRY));
                request.setAttribute(RequestParameter.CITY, fields.get(RequestParameter.CITY));
                request.setAttribute(Constant.ERROR_VACANCY_CREATION_ATTRIBUTE, Constant.ERROR_VACANCY_CREATION_MESSAGE);
                result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES + employeeId, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't create vacancy");
            throw new CommandException(e);
        }
        return result;
    }
}
