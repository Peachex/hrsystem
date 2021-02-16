package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.PagePath;
import com.epam.hrsystem.controller.UrlPattern;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements ActionCommand {
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        UserService service = UserServiceImpl.INSTANCE;
        CommandResult result;
        try {
            Optional<User> userOptional = service.login(email, password);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttribute.USER, user);
                session.setAttribute(SessionAttribute.CURRENT_ROLE, user.getRole());
                //fixme delete request setAttribute and change attribute in jsp to sessionScope
                request.setAttribute(SessionAttribute.USER, user);
                request.setAttribute(SessionAttribute.CURRENT_ROLE, user.getRole());
                result = new CommandResult(UrlPattern.HOME, CommandResult.Type.FORWARD);
            } else {
                request.setAttribute("errorData", "Email or password aren't valid");
                //todo create class Message
                result = new CommandResult(PagePath.LOGIN, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
