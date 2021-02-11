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

public class SaveUrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String previousUrl = request.getParameter(RequestParameter.PREVIOUS_URL);
        System.out.println(previousUrl);
        if (previousUrl != null && !previousUrl.isEmpty()) {
            session.setAttribute(SessionAttribute.PREVIOUS, previousUrl);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
