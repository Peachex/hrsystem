package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;;
import com.epam.hrsystem.controller.attribute.MailMessage;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.ApplicantState;
import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;
import com.epam.hrsystem.util.mail.MailSender;
import com.epam.hrsystem.validator.InterviewResultValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Action command creates interview result.
 *
 * @author Aleksey Klevitov
 */
public class CreateInterviewResultCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String APPLICANT_ID_COMMAND_PARAMETER = "&applicantId=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String applicantIdStr = request.getParameter(RequestParameter.APPLICANT_ID);
        String vacancyIdStr = request.getParameter(RequestParameter.VACANCY_ID);
        String rating = request.getParameter(RequestParameter.INTERVIEW_RESULT_RATING);
        String comment = request.getParameter(RequestParameter.INTERVIEW_RESULT_COMMENT);
        String newApplicantState = request.getParameter(RequestParameter.APPLICANT_STATE);

        Map<String, String> fields = new HashMap<>();
        fields.put(RequestParameter.INTERVIEW_RESULT_RATING, rating);
        fields.put(RequestParameter.INTERVIEW_RESULT_COMMENT, comment);
        fields.put(RequestParameter.APPLICANT_STATE, newApplicantState);

        ApplicantRequestService applicantRequestService = ServiceHolder.HOLDER.getApplicantRequestService();
        CommandResult result;
        try {
            long vacancyId = Long.parseLong(vacancyIdStr);
            long applicantId = Long.parseLong(applicantIdStr);
            if (applicantRequestService.createInterviewResult(fields, vacancyId, applicantId)) {
                String message;
                int applicantStateIndex = ApplicantState.valueOf(newApplicantState).ordinal();
                switch (applicantStateIndex) {
                    case 1: {
                        message = MailMessage.APPLICANT_IS_READY_FOR_TECHNICAL_INTERVIEW_MAIL_TEXT;
                        break;
                    }
                    case 2: {
                        message = MailMessage.APPLICANT_PASSED_INTERVIEWS_MAIL_TEXT;
                        break;
                    }
                    case 3: {
                        message = MailMessage.APPLICANT_FAILED_INTERVIEWS_MAIL_TEXT;
                        break;
                    }
                    default: {
                        message = null;
                    }
                }
                String applicantEmail = request.getParameter(RequestParameter.EMAIL);
                if (applicantEmail != null && message != null) {
                    MailSender mailSender = MailSender.MailSenderHolder.HOLDER.getMailSender();
                    mailSender.setupEmail(applicantEmail, MailMessage.HR_SYSTEM_MAIL_SUBJECT, message);
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
                if (!InterviewResultValidator.isInterviewResultFormValid(fields)) {
                    request.setAttribute(RequestParameter.INTERVIEW_RESULT_RATING, fields.get(RequestParameter.INTERVIEW_RESULT_RATING));
                    request.setAttribute(RequestParameter.INTERVIEW_RESULT_COMMENT, fields.get(RequestParameter.INTERVIEW_RESULT_COMMENT));
                    request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                } else {
                    request.setAttribute(JspAttribute.ERROR_INTERVIEW_RESULT_CREATION_ATTRIBUTE,
                            JspAttribute.ERROR_INTERVIEW_RESULT_DUPLICATE_CREATION_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't convert from string to long str = " + vacancyIdStr + " or " + applicantIdStr +
                    ": " + e);
            request.setAttribute(JspAttribute.NO_APPLICANT_REQUEST_ATTRIBUTE, JspAttribute.NO_APPLICANT_REQUEST_MESSAGE);
            result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't create interview result: " + e);
            throw new CommandException(e);
        }
        return result;
    }
}
