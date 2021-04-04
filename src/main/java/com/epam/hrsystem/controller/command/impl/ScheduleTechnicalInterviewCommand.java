package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.MailMessage;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;
import com.epam.hrsystem.util.mail.MailSender;
import com.epam.hrsystem.validator.ApplicantRequestValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ScheduleTechnicalInterviewCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String APPLICANT_ID_COMMAND_PARAMETER = "&applicantId=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String applicantIdStr = request.getParameter(RequestParameter.APPLICANT_ID);
        String vacancyIdStr = request.getParameter(RequestParameter.VACANCY_ID);
        String technicalInterviewDateStr = request.getParameter(RequestParameter.TECHNICAL_INTERVIEW_DATE);
        ApplicantRequestService applicantRequestService = ServiceHolder.HOLDER.getApplicantRequestService();
        CommandResult result;
        try {
            long vacancyId = Long.parseLong(vacancyIdStr);
            long applicantId = Long.parseLong(applicantIdStr);
            if (applicantRequestService.scheduleTechnicalInterview(technicalInterviewDateStr, vacancyId, applicantId)) {
                String applicantEmail = request.getParameter(RequestParameter.EMAIL);
                if (applicantEmail != null) {
                    MailSender mailSender = MailSender.MailSenderHolder.HOLDER.getMailSender();
                    StringBuilder mailMessage = new StringBuilder();
                    mailMessage.append(MailMessage.SCHEDULE_TECHNICAL_INTERVIEW_PART_1_MAIL_TEXT).append(technicalInterviewDateStr)
                            .append(MailMessage.SCHEDULE_TECHNICAL_INTERVIEW_PART_2_MAIL_TEXT);
                    mailSender.setupEmail(applicantEmail, MailMessage.HR_SYSTEM_MAIL_SUBJECT, mailMessage.toString());
                    mailSender.send();
                }
                result = new CommandResult(CommandName.TO_EMPLOYEE_APPLICANT_REQUEST + vacancyIdStr + APPLICANT_ID_COMMAND_PARAMETER +
                        applicantIdStr, CommandResult.Type.REDIRECT);
            } else {
                HttpSession session = request.getSession();
                long employeeId = (long) session.getAttribute(SessionAttribute.USER_ID);
                Optional<ApplicantRequest> applicantRequest = applicantRequestService.findApplicantRequestByVacancyIdAndApplicantId(vacancyId, applicantId);
                if (applicantRequest.isPresent() && applicantRequest.get().getVacancy().getEmployee().getId() == employeeId) {
                    result = new CommandResult(CommandName.TO_EMPLOYEE_APPLICANT_REQUEST + vacancyIdStr + APPLICANT_ID_COMMAND_PARAMETER +
                            applicantIdStr, CommandResult.Type.FORWARD);
                } else {
                    request.setAttribute(JspAttribute.NO_APPLICANT_REQUEST_ATTRIBUTE, JspAttribute.NO_APPLICANT_REQUEST_MESSAGE);
                    result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES, CommandResult.Type.FORWARD);
                }
                if (!ApplicantRequestValidator.isTechnicalInterviewDateValid(technicalInterviewDateStr)) {
                    request.setAttribute(RequestParameter.TECHNICAL_INTERVIEW_DATE, technicalInterviewDateStr);
                    request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                } else {
                    request.setAttribute(JspAttribute.ERROR_TECHNICAL_INTERVIEW_SCHEDULING_ATTRIBUTE,
                            JspAttribute.ERROR_TECHNICAL_INTERVIEW_SCHEDULING_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't convert from string to long str = " + vacancyIdStr + " or " + applicantIdStr +
                    ": " + e);
            request.setAttribute(JspAttribute.NO_APPLICANT_REQUEST_ATTRIBUTE, JspAttribute.NO_APPLICANT_REQUEST_MESSAGE);
            result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't schedule technical interview: " + e);
            throw new CommandException(e);
        }
        return result;
    }
}
