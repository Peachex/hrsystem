package com.epam.hrsystem.controller.filter;

import com.epam.hrsystem.controller.attribute.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

public class DoublePostingPreventingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final int MAX_RANDOM_RANGE = 10000;
    private static final String NAME_OF_GET_METHOD = "GET";
    private HttpSession session = null;
    private HttpServletRequest httpRequest = null;
    private int serverToken = 0;
    private int clientToken = 0;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        httpRequest = (HttpServletRequest) servletRequest;
        session = httpRequest.getSession(true);
        if (httpRequest.getMethod().equals(NAME_OF_GET_METHOD)) {
            session = httpRequest.getSession(true);
            session.setAttribute(SessionAttribute.SERVER_TOKEN, new Random().nextInt(MAX_RANDOM_RANGE));
            chain.doFilter(servletRequest, servletResponse);
        } else {
            serverToken = (Integer) session.getAttribute(SessionAttribute.SERVER_TOKEN);
            clientToken = Integer.parseInt(servletRequest.getParameter(SessionAttribute.CLIENT_TOKEN));
            if (serverToken == clientToken) {
                session.setAttribute(SessionAttribute.SERVER_TOKEN, new Random().nextInt(MAX_RANDOM_RANGE));
                chain.doFilter(servletRequest, servletResponse);
            } else {
                logger.log(Level.ERROR, "Couldn't execute double post servletRequest");
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher((String) session.getAttribute(SessionAttribute.CURRENT_PAGE));
                dispatcher.forward(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
