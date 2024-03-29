package com.epam.hrsystem.controller.listener;

import com.epam.hrsystem.controller.attribute.Locale;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.model.entity.UserRole;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web listener of session creation.
 *
 * @author Aleksey Klevitov
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(SessionAttribute.CURRENT_LOCALE, Locale.EN.getLocale());
        session.setAttribute(SessionAttribute.CURRENT_ROLE, UserRole.GUEST);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        sessionEvent.getSession().invalidate();
    }
}
