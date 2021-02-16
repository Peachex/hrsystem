package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.PagePath;
import com.epam.hrsystem.controller.UrlPattern;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.entity.comparator.VacancyComparator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

public class SortVacanciesByDateCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        CommandResult result = new CommandResult(PagePath.VACANCY, CommandResult.Type.FORWARD);
        Comparator<Vacancy> comparator = VacancyComparator.VACANCY_CREATION_DATE;
        ServletContext servletContext = request.getServletContext();
        List<Vacancy> vacancies = (List<Vacancy>) servletContext.getAttribute(RequestParameter.VACANCIES);
        String sortSequence = request.getParameter(RequestParameter.SORT_SEQUENCE);
        if (sortSequence != null && !sortSequence.isEmpty()) {
            if (sortSequence.equalsIgnoreCase("ASC")) {
                vacancies.sort(comparator);
            } else {
                vacancies.sort(comparator.reversed());
            }
        }
        if (vacancies.size() > 0) {
            request.setAttribute(RequestParameter.VACANCIES, vacancies);
        } else {
            //fixme magic text
            request.setAttribute(RequestParameter.NO_VACANCIES, "No vacancies");
        }
        return result;
    }
}
