package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.entity.comparator.VacancyComparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

/**
 * Action command sorts vacancies by creation date.
 *
 * @author Aleksey Klevitov
 */
public class SortVacanciesByDateCommand implements ActionCommand {
    private static final String SORT_SEQUENCE_ASC = "ASC";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Comparator<Vacancy> comparator = VacancyComparator.VACANCY_CREATION_DATE;
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Vacancy> vacancies = (List<Vacancy>) session.getAttribute(SessionAttribute.VACANCIES);

        String sortSequence = request.getParameter(RequestParameter.SORT_SEQUENCE);
        if (vacancies != null && vacancies.size() > 0 && sortSequence != null && !sortSequence.isEmpty()) {
            vacancies.sort(sortSequence.equalsIgnoreCase(SORT_SEQUENCE_ASC) ? comparator : comparator.reversed());
            request.setAttribute(RequestParameter.VACANCIES, vacancies);
        } else {
            request.setAttribute(JspAttribute.NO_VACANCIES_ATTRIBUTE, JspAttribute.NO_VACANCIES_MESSAGE);
        }
        return (new CommandResult(PagePath.VACANCY_LIST, CommandResult.Type.FORWARD));
    }
}
