package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateInterviewResultCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String newState = request.getParameter(RequestParameter.APPLICANT_STATE);
        System.out.println(newState + " !!!!!!!!!!!!!!!");
        CommandResult result = new CommandResult(PagePath.EMPLOYEE_APPLICANT_REQUEST, CommandResult.Type.FORWARD);
        return result;
    }
}
