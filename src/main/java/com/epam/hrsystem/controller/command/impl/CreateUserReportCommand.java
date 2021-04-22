package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.MailMessage;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.ServletAttribute;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.UserReportService;
import com.epam.hrsystem.model.service.impl.UserReportServiceImpl;
import com.epam.hrsystem.util.mail.MailSender;
import com.epam.hrsystem.validator.UserReportValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Action command creates user report.
 *
 * @author Aleksey Klevitov
 */
public class CreateUserReportCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute(SessionAttribute.USER_ID);
        String subject = request.getParameter(RequestParameter.USER_REPORT_SUBJECT);
        String comment = request.getParameter(RequestParameter.USER_REPORT_COMMENT);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.USER_REPORT_SUBJECT, subject);
        fields.put(RequestParameter.USER_REPORT_COMMENT, comment);

        UserReportService service = UserReportServiceImpl.getInstance();
        CommandResult result = new CommandResult(ServletAttribute.HOME_URL_PATTERN, CommandResult.Type.REDIRECT);
        try {
            if (service.createUserReport(fields, userId)) {
                MailSender mailSender = MailSender.MailSenderHolder.HOLDER.getMailSender();
                User user = (User) session.getAttribute(SessionAttribute.USER);
                mailSender.setupEmail(user.getEmail(), MailMessage.HR_SYSTEM_MAIL_SUBJECT, MailMessage.CREATION_USER_REPORT_MAIL_TEXT);
                mailSender.send();
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, JspAttribute.SUCCESS_MESSAGE);
            } else {
                if (UserReportValidator.isUserReportFormValid(fields)) {
                    request.setAttribute(JspAttribute.ERROR_DUPLICATE_ATTRIBUTE, JspAttribute.ERROR_USER_REPORT_DUPLICATE_MESSAGE);
                } else {
                    request.setAttribute(RequestParameter.USER_REPORT_SUBJECT, fields.get(RequestParameter.USER_REPORT_SUBJECT));
                    request.setAttribute(RequestParameter.USER_REPORT_COMMENT, fields.get(RequestParameter.USER_REPORT_COMMENT));
                    request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                }
                result = new CommandResult(ServletAttribute.HOME_URL_PATTERN, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't create user report");
            throw new CommandException(e);
        }
        return result;
    }
}
