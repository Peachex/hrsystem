package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ToEmployeeVacancyInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String vacancyId = request.getParameter(RequestParameter.VACANCY_ID);
        VacancyService vacancyService = ServiceHolder.HOLDER.getVacancyService();
        ApplicantRequestService applicantRequestService = ServiceHolder.HOLDER.getApplicantRequestService();
        CommandResult result;
        try {
            long id = Long.parseLong(vacancyId);
            Optional<Vacancy> vacancyOptional = vacancyService.findVacancyById(id);
            if (vacancyOptional.isPresent()) {
                HttpSession session = request.getSession();
                Vacancy vacancy = vacancyOptional.get();
                if (vacancy.getEmployee().getId() == (long) session.getAttribute(SessionAttribute.USER_ID)) {
                    List<ApplicantRequest> applicantRequests = applicantRequestService.findApplicantRequestsByVacancyId(vacancy.getId());
                    if (applicantRequests.size() > 0) {
                        request.setAttribute(RequestParameter.APPLICANT_REQUESTS, applicantRequests);
                    } else {
                        request.setAttribute(JspAttribute.NO_APPLICANT_REQUESTS_ATTRIBUTE, JspAttribute.NO_APPLICANT_REQUESTS_MESSAGE);
                    }
                    request.setAttribute(RequestParameter.VACANCY, vacancy);
                    result = new CommandResult(PagePath.EMPLOYEE_CURRENT_VACANCY_INFO, CommandResult.Type.FORWARD);
                } else {
                    result = new CommandResult(PagePath.EMPLOYEE_VACANCY_LIST, CommandResult.Type.FORWARD);
                    request.setAttribute(JspAttribute.ERROR_STRANGE_VACANCY_ATTRIBUTE, JspAttribute.ERROR_STRANGE_VACANCY_MESSAGE);
                }
            } else {
                result = new CommandResult(PagePath.EMPLOYEE_VACANCY_LIST, CommandResult.Type.FORWARD);
                request.setAttribute(JspAttribute.NO_VACANCY_ATTRIBUTE, JspAttribute.NO_VACANCY_MESSAGE);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't convert from string to long str = " + vacancyId + ": " + e);
            request.setAttribute(JspAttribute.NO_VACANCY_ATTRIBUTE, JspAttribute.NO_VACANCY_MESSAGE);
            result = new CommandResult(PagePath.EMPLOYEE_VACANCY_LIST, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return result;
    }
}
