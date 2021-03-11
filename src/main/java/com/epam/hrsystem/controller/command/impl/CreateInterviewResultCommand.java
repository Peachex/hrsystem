package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;
import com.epam.hrsystem.validator.InterviewResultValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CreateInterviewResultCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String APPLICANT_ID_COMMAND_PARAMETER = "&applicantId=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        //fixme rewrite function
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
        long vacancyId = Long.parseLong(vacancyIdStr);
        long applicantId = Long.parseLong(applicantIdStr);
        try {
            if (applicantRequestService.createInterviewResult(fields, vacancyId, applicantId)) {
                result = new CommandResult(CommandName.TO_EMPLOYEE_APPLICANT_REQUEST + vacancyIdStr + APPLICANT_ID_COMMAND_PARAMETER +
                        applicantIdStr, CommandResult.Type.REDIRECT);
                //todo add message sending
            } else {
                result = new CommandResult(CommandResult.Type.RETURN_WITH_FORWARD);
                if (!InterviewResultValidator.isInterviewResultFormValid(fields)) {
                    request.setAttribute(RequestParameter.INTERVIEW_RESULT_RATING, fields.get(RequestParameter.INTERVIEW_RESULT_RATING));
                    request.setAttribute(RequestParameter.INTERVIEW_RESULT_COMMENT, fields.get(RequestParameter.INTERVIEW_RESULT_COMMENT));
                    request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                } else {
                    request.setAttribute(JspAttribute.ERROR_INTERVIEW_RESULT_CREATION_ATTRIBUTE,
                            JspAttribute.ERROR_INTERVIEW_RESULT_DUPLICATE_CREATION_MESSAGE);
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Couldn't create interview result: " + e);
            throw new CommandException(e);
        }
        return result;
    }
}
