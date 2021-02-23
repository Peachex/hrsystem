package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.Constant;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.entity.comparator.VacancyComparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

public class SortVacanciesByDateCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        CommandResult result = new CommandResult(PagePath.VACANCY_LIST, CommandResult.Type.FORWARD);
        Comparator<Vacancy> comparator = VacancyComparator.VACANCY_CREATION_DATE;
        HttpSession session = request.getSession();
        List<Vacancy> vacancies = (List<Vacancy>) session.getAttribute(SessionAttribute.VACANCIES);
        String sortSequence = request.getParameter(RequestParameter.SORT_SEQUENCE);
        if (vacancies != null && vacancies.size() > 0 && sortSequence != null && !sortSequence.isEmpty()) {
            if (sortSequence.equalsIgnoreCase(Constant.SORT_SEQUENCE_ASC)) {
                vacancies.sort(comparator);
            } else {
                vacancies.sort(comparator.reversed());
            }
            request.setAttribute(RequestParameter.VACANCIES, vacancies);
        } else {
            request.setAttribute(Constant.NO_VACANCIES_ATTRIBUTE, Constant.NO_VACANCIES_MESSAGE);
        }
        return result;
    }
}
