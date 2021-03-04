package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.model.service.impl.VacancyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToVacanciesCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        VacancyService service = VacancyServiceImpl.INSTANCE;
        CommandResult result = new CommandResult(PagePath.VACANCY_LIST, CommandResult.Type.FORWARD);
        try {
            List<Vacancy> vacancies = service.findAvailableVacancies();
            if (vacancies.size() > 0) {
                request.setAttribute(RequestParameter.VACANCIES, vacancies);
            } else {
                HttpSession session = request.getSession();
                session.removeAttribute(SessionAttribute.VACANCIES);
                request.setAttribute(JspAttribute.NO_VACANCIES_ATTRIBUTE, JspAttribute.NO_VACANCIES_BY_REQUEST_MESSAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
