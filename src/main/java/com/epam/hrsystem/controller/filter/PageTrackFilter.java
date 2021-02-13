package com.epam.hrsystem.controller.filter;

import com.epam.hrsystem.controller.attribute.RequestParameter;
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
    private static final String SLASH = "/";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String currentPage = getRequestWithAllParameters(request);
        String previousPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        if (!currentPage.equals(previousPage)) {
            session.setAttribute(SessionAttribute.PREVIOUS_PAGE, previousPage);
            session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        }
        //fixme filter returns previous url only of command

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private String getRequestWithAllParameters(HttpServletRequest request) {
        String str = request.getRequestURL() + "?";
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                str = str + paramName + "=" + paramValue;
            }
            str = str + "&";
        }
        String result = str.substring(0, str.length() - 1);
        return result;
    }
}
