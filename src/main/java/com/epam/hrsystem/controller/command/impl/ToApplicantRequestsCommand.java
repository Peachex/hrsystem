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
import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToApplicantRequestsCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        long applicantId = (long) session.getAttribute(SessionAttribute.USER_ID);
        ApplicantRequestService service = ServiceHolder.HOLDER.getApplicantRequestService();
        CommandResult result = new CommandResult(PagePath.APPLICANT_REQUESTS, CommandResult.Type.FORWARD);
        try {
            List<ApplicantRequest> applicantRequests = service.findApplicantRequestsByApplicantId(applicantId);
            if (applicantRequests.size() > 0) {
                request.setAttribute(RequestParameter.APPLICANT_REQUESTS, applicantRequests);
            } else {
                request.setAttribute(JspAttribute.NO_APPLICANT_REQUESTS_ATTRIBUTE, JspAttribute.NO_APPLICANT_REQUESTS_MESSAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
