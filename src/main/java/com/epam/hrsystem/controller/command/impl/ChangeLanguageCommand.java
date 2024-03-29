package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.Locale;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Action command changes language.
 *
 * @author Aleksey Klevitov
 */
public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        String newLocaleStr = request.getParameter(RequestParameter.NEW_LOCALE);
        Locale newLocale = Locale.defineLocale(newLocaleStr);
        session.setAttribute(SessionAttribute.CURRENT_LOCALE, newLocale.getLocale());
        return (new CommandResult(CommandResult.Type.RETURN_WITH_REDIRECT));
    }
}
