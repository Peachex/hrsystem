package com.epam.hrsystem.controller.filter;

import com.epam.hrsystem.controller.attribute.SessionAttribute;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class PageTrackFilter implements Filter {
    private static final String AMPERSAND = "&";
    private static final String EQUALS_SIGN = "=";
    private static final String QUESTION_SIGN = "?";
    private static final String DEFAULT_URL = "http://localhost:8081/hrsystem_war_exploded/";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        //fixme current page maybe unnecessary
        String currentPage = getRequestWithAllParameters(request);
        String previousPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);

        if (!currentPage.equals(previousPage)) {
            session.setAttribute(SessionAttribute.PREVIOUS_PAGE, previousPage);
            session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private String getRequestWithAllParameters(HttpServletRequest request) {
        String action = request.getRequestURL().substring(DEFAULT_URL.length());
        StringBuilder sb = new StringBuilder(action + QUESTION_SIGN);
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for (String paramValue : paramValues) {
                sb.append(paramName).append(EQUALS_SIGN).append(paramValue);
            }
            sb.append(AMPERSAND);
        }
        String result = sb.substring(0, sb.length() - 1);
        return result;
    }
}
