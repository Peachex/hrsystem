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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteVacancyCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        VacancyService service = ServiceHolder.HOLDER.getVacancyService();
        HttpSession session = request.getSession();
        CommandResult result;
        String vacancyIdStr = request.getParameter(RequestParameter.VACANCY_ID);
        try {
            long vacancyId = Long.parseLong(vacancyIdStr);
            long employeeId = (long) session.getAttribute(SessionAttribute.USER_ID);
            boolean isDeleted = service.deleteVacancy(vacancyId, employeeId);
            if (isDeleted) {
                result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCY_INFO + vacancyIdStr, CommandResult.Type.REDIRECT);
            } else {
                result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES + employeeId, CommandResult.Type.FORWARD);
                request.setAttribute(JspAttribute.ERROR_VACANCY_DELETE_ATTRIBUTE, JspAttribute.ERROR_VACANCY_DELETE_MESSAGE);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't delete vacancy with id = " + vacancyIdStr + ": " + e);
            throw new CommandException(e);
        }
        return result;
    }
}
