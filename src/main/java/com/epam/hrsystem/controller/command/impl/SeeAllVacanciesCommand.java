package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.UrlPattern;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.model.service.impl.VacancyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SeeAllVacanciesCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        VacancyService service = VacancyServiceImpl.INSTANCE;
        CommandResult result = new CommandResult(UrlPattern.VACANCY, CommandResult.Type.FORWARD);
        try {
            List<Vacancy> vacancies = service.findAllVacancies();
            if (vacancies.size() > 0) {
                //fixme maybe servlet context?
                request.setAttribute(RequestParameter.VACANCIES, vacancies);
            } else {
                //fixme magic text
                request.setAttribute(RequestParameter.NO_VACANCIES, "No vacancies");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
