package com.epam.hrsystem.controller.command.impl;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Action command finds vacancies by key word.
 *
 * @author Aleksey Klevitov
 */
public class FindVacanciesByKeyWordCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        VacancyService service = ServiceHolder.HOLDER.getVacancyService();
        CommandResult result = new CommandResult(PagePath.VACANCY_LIST, CommandResult.Type.FORWARD);
        try {
            String keyWord = request.getParameter(RequestParameter.KEY_WORD);
            List<Vacancy> vacancies = service.findVacanciesByKeyWord(keyWord);
            if (vacancies.size() == 0) {
                request.setAttribute(JspAttribute.NO_VACANCIES_ATTRIBUTE, JspAttribute.NO_VACANCIES_BY_REQUEST_MESSAGE);
            }
            request.setAttribute(RequestParameter.VACANCIES, vacancies);
            request.setAttribute(RequestParameter.KEY_WORD, keyWord);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
