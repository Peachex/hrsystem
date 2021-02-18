package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.PagePath;
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

public class ToVacancyCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        //fixme
        VacancyService service = VacancyServiceImpl.INSTANCE;
        CommandResult result = new CommandResult(PagePath.VACANCY, CommandResult.Type.FORWARD);
        try {
            List<Vacancy> vacancies = service.findAvailableVacancies();
            if (vacancies.size() > 0) {
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
