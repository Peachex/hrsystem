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
import javax.servlet.http.HttpSession;

public class RestoreVacancyCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        //fixme
        VacancyService service = VacancyServiceImpl.INSTANCE;
        CommandResult result = new CommandResult(CommandName.TO_VACANCY, CommandResult.Type.FORWARD);
        try {
            String vacancyId = request.getParameter(RequestParameter.VACANCY_ID);
            long id = Long.parseLong(vacancyId);
            boolean isRestored = service.restoreVacancy(id);
            if (!isRestored) {
                HttpSession session = request.getSession();
                String previousPage = (String) session.getAttribute(SessionAttribute.PREVIOUS_PAGE);
                result = new CommandResult(previousPage, CommandResult.Type.FORWARD);
                request.setAttribute(Constant.ERROR_VACANCY_RESTORE_ATTRIBUTE, Constant.ERROR_VACANCY_RESTORE_MESSAGE);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't restore vacancy");
            throw new CommandException(e);
        }
        return result;
    }
}
