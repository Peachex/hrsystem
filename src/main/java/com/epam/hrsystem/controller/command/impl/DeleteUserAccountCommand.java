package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.ServletAttribute;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserRole;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Action command deletes user account.
 *
 * @author Aleksey Klevitov
 */
public class DeleteUserAccountCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String currentPassword = request.getParameter(RequestParameter.CURRENT_PASSWORD);
        CommandResult result;
        try {
            Optional<User> userOptional = service.login(user.getEmail(), currentPassword);
            if (userOptional.isPresent()) {
                service.blockUser(user.getId());
                session.removeAttribute(SessionAttribute.USER_ID);
                session.removeAttribute(SessionAttribute.USER);
                session.setAttribute(SessionAttribute.CURRENT_ROLE, UserRole.GUEST);
                result = new CommandResult(ServletAttribute.HOME_URL_PATTERN, CommandResult.Type.REDIRECT);
            } else {
                request.setAttribute(JspAttribute.ERROR_INVALID_CURRENT_PASSWORD_ATTRIBUTE, JspAttribute.ERROR_INVALID_CURRENT_PASSWORD_MESSAGE);
                result = new CommandResult(CommandName.TO_USER_PROFILE, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't delete user account");
            throw new CommandException(e);
        }
        return result;
    }
}
