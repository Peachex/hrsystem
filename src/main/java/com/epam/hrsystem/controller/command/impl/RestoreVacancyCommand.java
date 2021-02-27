package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.Constant;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestoreVacancyCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        VacancyService service = VacancyServiceImpl.INSTANCE;
        HttpSession session = request.getSession();
        CommandResult result;
        String vacancyIdStr = request.getParameter(RequestParameter.VACANCY_ID);
        try {
            long vacancyId = Long.parseLong(vacancyIdStr);
            long employeeId = (long) session.getAttribute(SessionAttribute.USER_ID);
            boolean isRestored = service.restoreVacancy(vacancyId, employeeId);
            if (isRestored) {
                result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCY_INFO + vacancyIdStr, CommandResult.Type.REDIRECT);
            } else {
                result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES + employeeId, CommandResult.Type.FORWARD);
                request.setAttribute(Constant.ERROR_VACANCY_RESTORE_ATTRIBUTE, Constant.ERROR_VACANCY_RESTORE_MESSAGE);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't restore vacancy with id = " + vacancyIdStr + ": " + e);
            throw new CommandException(e);
        }
        return result;
    }
}
