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
import com.epam.hrsystem.model.service.impl.ServiceHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SeeEmployeeVacanciesWithApplicantsRequestsCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        VacancyService service = ServiceHolder.HOLDER.getVacancyService();
        CommandResult result = new CommandResult(PagePath.EMPLOYEE_VACANCY_LIST, CommandResult.Type.FORWARD);
        HttpSession session = request.getSession();
        try {
            long employeeId = (long) session.getAttribute(SessionAttribute.USER_ID);
            List<Vacancy> vacancies = service.findEmployeeVacanciesWithApplicantsRequests(employeeId);
            if (vacancies.size() > 0) {
                request.setAttribute(RequestParameter.EMPLOYEE_VACANCIES, vacancies);
            } else {
                request.setAttribute(JspAttribute.NO_VACANCIES_ATTRIBUTE, JspAttribute.NO_VACANCIES_MESSAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
