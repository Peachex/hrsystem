package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.MailMessage;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.UserReport;
import com.epam.hrsystem.model.service.UserReportService;
import com.epam.hrsystem.model.service.impl.UserReportServiceImpl;
import com.epam.hrsystem.util.mail.MailSender;
import com.epam.hrsystem.validator.UserReportValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action command creates user report response.
 *
 * @author Aleksey Klevitov
 */
public class CreateUserReportResponseCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String userReportResponse = request.getParameter(RequestParameter.USER_REPORT_RESPONSE);
        String reportIdStr = request.getParameter(RequestParameter.REPORT_ID);
        UserReportService service = UserReportServiceImpl.getInstance();
        CommandResult result = new CommandResult(CommandName.TO_ADMIN_USER_REPORT_INFO + reportIdStr, CommandResult.Type.REDIRECT);
        try {
            long reportId = Long.parseLong(reportIdStr);
            if (service.createResponse(reportId, userReportResponse)) {
                UserReport report = service.findUserReportById(reportId).get();
                String email = report.getUser().getEmail();
                String message = MailMessage.CREATION_USER_REPORT_RESPONSE_MAIL_TEXT + userReportResponse;
                MailSender sender = MailSender.MailSenderHolder.HOLDER.getMailSender();
                sender.setupEmail(email, MailMessage.HR_SYSTEM_MAIL_SUBJECT, message);
                sender.send();
            } else {
                if (UserReportValidator.isResponseValid(userReportResponse)) {
                    request.setAttribute(JspAttribute.ERROR_DUPLICATE_ATTRIBUTE, JspAttribute.ERROR_USER_REPORT_DUPLICATE_MESSAGE);
                } else {
                    request.setAttribute(RequestParameter.USER_REPORT_RESPONSE, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
                    request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                }
                result = new CommandResult(CommandName.TO_ADMIN_USER_REPORT_INFO + reportIdStr, CommandResult.Type.FORWARD);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't convert from string to long str = " + reportIdStr + e);
            request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
            result = new CommandResult(CommandName.TO_ADMIN_USER_REPORT_LIST, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't create response");
            throw new CommandException(e);
        }
        return result;
    }
}
