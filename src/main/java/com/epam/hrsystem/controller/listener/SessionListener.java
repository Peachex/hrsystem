package com.epam.hrsystem.controller.listener;

import com.epam.hrsystem.controller.UrlPattern;
import com.epam.hrsystem.controller.attribute.Locale;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.model.entity.UserRole;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        //fixme current page maybe unnecessary
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(SessionAttribute.CURRENT_LOCALE, Locale.RU.getLocale());
        session.setAttribute(SessionAttribute.CURRENT_ROLE, UserRole.GUEST);
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, UrlPattern.HOME);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, UrlPattern.HOME);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        sessionEvent.getSession().invalidate();
    }
}
