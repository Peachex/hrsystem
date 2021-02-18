package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.Constant;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateApplicantRequestCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        //fixme
        String summary = request.getParameter(RequestParameter.SUMMARY);
        HttpSession session = request.getSession();
        User applicant = (User) session.getAttribute(SessionAttribute.USER);
        String vacancyIdStr = request.getParameter(RequestParameter.VACANCY_ID);
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.SUMMARY, summary);
        fields.put(RequestParameter.VACANCY_ID, vacancyIdStr);
        CommandResult result;
        try {
            ApplicantRequestService service = ApplicantRequestServiceImpl.INSTANCE;
            if (service.createApplicantRequest(fields, applicant)) {
                MailSender mailSender = MailSender.INSTANCE;
                mailSender.setSendToEmail(applicant.getEmail());
                mailSender.setMailSubject(Constant.CREATION_APPLICANT_REQUEST_MAIL_SUBJECT);
                mailSender.setMailText(Constant.CREATION_APPLICANT_REQUEST_MAIL_TEXT);
                mailSender.send();
                result = new CommandResult(CommandName.TO_VACANCY, CommandResult.Type.FORWARD);
            } else {
                result = new CommandResult((String) session.getAttribute(SessionAttribute.PREVIOUS_PAGE), CommandResult.Type.FORWARD);
                request.setAttribute(Constant.ERROR_APPLICANT_REQUEST_CREATION_ATTRIBUTE, Constant.ERROR_APPLICANT_REQUEST_CREATION_MESSAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't create applicant request");
            throw new CommandException(e);
        }
        return result;
    }
}
