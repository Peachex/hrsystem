package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.PagePath;
import com.epam.hrsystem.controller.attribute.ServletAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        UserService service = ServiceHolder.HOLDER.getUserService();
        CommandResult result;
        try {
            Optional<User> userOptional = service.login(email, password);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttribute.USER, user);
                session.setAttribute(SessionAttribute.CURRENT_ROLE, user.getRole());
                session.setAttribute(SessionAttribute.USER_ID, user.getId());
                result = new CommandResult(ServletAttribute.HOME_URL_PATTERN, CommandResult.Type.REDIRECT);
            } else {
                request.setAttribute(RequestParameter.EMAIL, email);
                request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                result = new CommandResult(PagePath.LOGIN, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
