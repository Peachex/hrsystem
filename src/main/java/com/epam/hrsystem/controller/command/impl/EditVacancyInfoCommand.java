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
import com.epam.hrsystem.model.service.impl.ServiceHolder;
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
 * Action command edits vacancy info.
 *
 * @author Aleksey Klevitov
 */
public class EditVacancyInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        long employeeId = (long) session.getAttribute(SessionAttribute.USER_ID);
        String newPosition = request.getParameter(RequestParameter.POSITION);
        String newDescription = request.getParameter(RequestParameter.DESCRIPTION);
        String newCountry = request.getParameter(RequestParameter.COUNTRY);
        String newCity = request.getParameter(RequestParameter.CITY);
        String vacancyIdStr = request.getParameter(RequestParameter.VACANCY_ID);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.POSITION, newPosition);
        fields.put(RequestParameter.DESCRIPTION, newDescription);
        fields.put(RequestParameter.COUNTRY, newCountry);
        fields.put(RequestParameter.CITY, newCity);

        VacancyService service = ServiceHolder.HOLDER.getVacancyService();
        CommandResult result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCY_INFO + vacancyIdStr, CommandResult.Type.REDIRECT);
        try {
            long vacancyId = Long.parseLong(vacancyIdStr);
            if (!service.updateVacancyInfo(vacancyId, employeeId, fields)) {
                if (!VacancyValidator.isVacancyFormValid(fields)) {
                    request.setAttribute(RequestParameter.POSITION, fields.get(RequestParameter.POSITION));
                    request.setAttribute(RequestParameter.DESCRIPTION, fields.get(RequestParameter.DESCRIPTION));
                    request.setAttribute(RequestParameter.COUNTRY, fields.get(RequestParameter.COUNTRY));
                    request.setAttribute(RequestParameter.CITY, fields.get(RequestParameter.CITY));
                    request.setAttribute(JspAttribute.ERROR_VACANCY_UPDATING_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                } else {
                    request.setAttribute(JspAttribute.ERROR_VACANCY_UPDATING_ATTRIBUTE, JspAttribute.ERROR_VACANCY_UPDATING_MESSAGE);
                }
                result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCY_INFO + vacancyIdStr, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't create vacancy");
            throw new CommandException(e);
        }
        return result;
    }
}
