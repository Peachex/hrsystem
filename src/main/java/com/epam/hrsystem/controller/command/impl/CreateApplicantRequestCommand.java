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
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.impl.ApplicantRequestServiceImpl;
import com.epam.hrsystem.util.mail.MailSender;
import com.epam.hrsystem.validator.ApplicantRequestValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Action command creates applicant request.
 *
 * @author Aleksey Klevitov
 */
public class CreateApplicantRequestCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        User applicant = (User) session.getAttribute(SessionAttribute.USER);
        String summary = request.getParameter(RequestParameter.SUMMARY);
        String vacancyIdStr = request.getParameter(RequestParameter.VACANCY_ID);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.SUMMARY, summary);
        fields.put(RequestParameter.VACANCY_ID, vacancyIdStr);

        CommandResult result = new CommandResult(CommandName.TO_APPLICANT_REQUESTS, CommandResult.Type.REDIRECT);
        ApplicantRequestService service = ApplicantRequestServiceImpl.getInstance();
        try {
            if (service.createApplicantRequest(fields, applicant)) {
                MailSender mailSender = MailSender.MailSenderHolder.HOLDER.getMailSender();
                String applicantEmail = applicant.getEmail();
                mailSender.setupEmail(applicantEmail, MailMessage.HR_SYSTEM_MAIL_SUBJECT, MailMessage.CREATION_APPLICANT_REQUEST_MAIL_TEXT);
                mailSender.send();
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
            } else {
                if (ApplicantRequestValidator.isSummaryValid(summary)) {
                    request.setAttribute(JspAttribute.ERROR_DUPLICATE_ATTRIBUTE, JspAttribute.ERROR_APPLICANT_REQUEST_DUPLICATE_MESSAGE);
                } else {
                    request.setAttribute(JspAttribute.ERROR_APPLICANT_REQUEST_CREATION_ATTRIBUTE, JspAttribute.ERROR_APPLICANT_REQUEST_CREATION_MESSAGE);
                }
                result = new CommandResult(CommandName.TO_VACANCY_INFO + vacancyIdStr, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't create applicant request");
            throw new CommandException(e);
        }
        return result;
    }
}
