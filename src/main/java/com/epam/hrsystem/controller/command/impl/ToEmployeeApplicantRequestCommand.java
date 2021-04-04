package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ToEmployeeApplicantRequestCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String applicantIdStr = request.getParameter(RequestParameter.APPLICANT_ID);
        String vacancyIdStr = request.getParameter(RequestParameter.VACANCY_ID);
        ApplicantRequestService service = ServiceHolder.HOLDER.getApplicantRequestService();
        CommandResult result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES, CommandResult.Type.FORWARD);
        try {
            long applicantId = Long.parseLong(applicantIdStr);
            long vacancyId = Long.parseLong(vacancyIdStr);
            Optional<ApplicantRequest> applicantRequestOptional = service.findApplicantRequestByVacancyIdAndApplicantId(vacancyId, applicantId);
            if (applicantRequestOptional.isPresent()) {
                HttpSession session = request.getSession();
                long employeeId = (long) session.getAttribute(SessionAttribute.USER_ID);
                ApplicantRequest applicantRequest = applicantRequestOptional.get();
                if (applicantRequest.getVacancy().getEmployee().getId() == employeeId) {
                    request.setAttribute(RequestParameter.APPLICANT_REQUEST, applicantRequest);
                    result = new CommandResult(PagePath.EMPLOYEE_APPLICANT_REQUEST, CommandResult.Type.FORWARD);
                } else {
                    request.setAttribute(JspAttribute.ERROR_STRANGE_VACANCY_ATTRIBUTE, JspAttribute.ERROR_STRANGE_VACANCY_MESSAGE);
                }
            } else {
                request.setAttribute(JspAttribute.NO_APPLICANT_REQUEST_ATTRIBUTE, JspAttribute.NO_APPLICANT_REQUEST_MESSAGE);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't convert from string to long str = " + applicantIdStr + " or " + vacancyIdStr +
                    ": " + e);
            request.setAttribute(JspAttribute.NO_VACANCY_ATTRIBUTE, JspAttribute.NO_VACANCY_MESSAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return result;
    }
}
