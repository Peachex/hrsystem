package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.UserReport;
import com.epam.hrsystem.model.service.UserReportService;
import com.epam.hrsystem.model.service.impl.UserReportServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Action command redirects to user report info page.
 *
 * @author Aleksey Klevitov
 */
public class ToAdminUserReportInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String reportIdStr = request.getParameter(RequestParameter.REPORT_ID);
        UserReportService service = UserReportServiceImpl.getInstance();
        CommandResult result = new CommandResult(CommandName.TO_ADMIN_USER_REPORT_LIST, CommandResult.Type.FORWARD);
        try {
            long reportId = Long.parseLong(reportIdStr);
            Optional<UserReport> reportOptional = service.findUserReportById(reportId);
            if (reportOptional.isPresent()) {
                UserReport report = reportOptional.get();
                request.setAttribute(RequestParameter.REPORT, report);
                HttpSession session = request.getSession();
                session.setAttribute(RequestParameter.REPORT_ID, reportId);
                result = new CommandResult(PagePath.ADMIN_USER_REPORT_INFO, CommandResult.Type.FORWARD);
            } else {
                request.setAttribute(JspAttribute.NO_REPORT_ATTRIBUTE, JspAttribute.NO_REPORT_MESSAGE);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't convert from string to long str = " + reportIdStr + ": " + e);
            request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return result;
    }
}
