package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.attribute.Locale;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        CommandResult result = new CommandResult((String) session.getAttribute(SessionAttribute.PREVIOUS_PAGE), CommandResult.Type.FORWARD);
        String currentLocale = (String) session.getAttribute(SessionAttribute.CURRENT_LOCALE);
        if (currentLocale.equals(Locale.EN.getLocale())) {
            session.setAttribute(SessionAttribute.CURRENT_LOCALE, Locale.RU.getLocale());
        } else {
            session.setAttribute(SessionAttribute.CURRENT_LOCALE, Locale.EN.getLocale());
        }
        return result;
    }
}
