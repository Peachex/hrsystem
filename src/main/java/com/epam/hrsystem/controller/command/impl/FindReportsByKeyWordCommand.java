package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.UserReport;
import com.epam.hrsystem.model.service.UserReportService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindReportsByKeyWordCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserReportService service = ServiceHolder.HOLDER.getUserReportService();
        CommandResult result = new CommandResult(PagePath.ADMIN_USER_REPORT_LIST, CommandResult.Type.FORWARD);
        try {
            String keyWord = request.getParameter(RequestParameter.KEY_WORD);
            List<UserReport> reports = service.findUserReportsByKeyWord(keyWord);
            if (reports.size() == 0) {
                request.setAttribute(JspAttribute.NO_REPORTS_ATTRIBUTE, JspAttribute.NO_REPORTS_BY_REQUEST_MESSAGE);
            }
            request.setAttribute(RequestParameter.REPORTS, reports);
            request.setAttribute(RequestParameter.KEY_WORD, keyWord);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
