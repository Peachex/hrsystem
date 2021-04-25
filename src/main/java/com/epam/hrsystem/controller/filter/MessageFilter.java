package com.epam.hrsystem.controller.filter;

import com.epam.hrsystem.controller.attribute.JspAttribute;
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

/**
 * Filter moves a success message to the jsp page and removes this message from the session.
 *
 * @author Aleksey Klevitov
 */
public class MessageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String successMessage = (String) session.getAttribute(SessionAttribute.SUCCESS_MESSAGE);
        if (successMessage != null) {
            request.setAttribute(JspAttribute.SUCCESS_ATTRIBUTE, successMessage);
        }
        session.removeAttribute(SessionAttribute.SUCCESS_MESSAGE);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
