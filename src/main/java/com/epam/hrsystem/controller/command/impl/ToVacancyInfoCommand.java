package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Action command redirects to vacancy info page.
 *
 * @author Aleksey Klevitov
 */
public class ToVacancyInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String vacancyId = request.getParameter(RequestParameter.VACANCY_ID);
        VacancyService service = ServiceHolder.HOLDER.getVacancyService();
        CommandResult result = new CommandResult(CommandName.TO_VACANCIES, CommandResult.Type.FORWARD);
        try {
            try {
                long id = Long.parseLong(vacancyId);
                Optional<Vacancy> vacancyOptional = service.findVacancyById(id);
                if (vacancyOptional.isPresent()) {
                    Vacancy vacancy = vacancyOptional.get();
                    request.setAttribute(RequestParameter.VACANCY, vacancy);
                    result = new CommandResult(PagePath.CURRENT_VACANCY_INFO, CommandResult.Type.FORWARD);
                } else {
                    request.setAttribute(JspAttribute.NO_VACANCY_ATTRIBUTE, JspAttribute.NO_VACANCY_MESSAGE);
                }
            } catch (NumberFormatException e) {
                logger.log(Level.ERROR, "Couldn't convert from string to long str = " + vacancyId + ": " + e);
                request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return result;
    }
}
