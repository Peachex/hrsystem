package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.Constant;
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
import javax.servlet.http.HttpSession;
import java.util.List;

public class SeeEmployeeVacanciesWithActiveApplicantsRequestsCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        VacancyService service = VacancyServiceImpl.INSTANCE;
        CommandResult result = new CommandResult(PagePath.EMPLOYEE_VACANCY_LIST, CommandResult.Type.FORWARD);
        HttpSession session = request.getSession();
        try {
            long employeeId = (long) session.getAttribute(SessionAttribute.USER_ID);
            List<Vacancy> vacancies = service.findEmployeeVacanciesWithActiveApplicantsRequests(employeeId);
            if (vacancies.size() > 0) {
                request.setAttribute(RequestParameter.EMPLOYEE_VACANCIES, vacancies);
            } else {
                request.setAttribute(Constant.NO_VACANCIES_ATTRIBUTE, Constant.NO_VACANCIES_MESSAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
