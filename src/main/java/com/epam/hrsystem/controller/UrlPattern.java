package com.epam.hrsystem.controller;

public class UrlPattern {
    public static final String CONTROLLER = "*.do";
    public static final String UPLOAD_SERVLET = "*.upload";
    public static final String HOME = "/home";
    public static final String LOGIN = "/WEB-INF/jsp/authorization/login.jsp";
    public static final String VACANCY = "/WEB-INF/jsp/vacancy/vacancy.jsp";
    public static final String REGISTER = "/WEB-INF/jsp/authorization/register.jsp";
    public static final String DOUBLE_POSTING_ERROR = "/WEB-INF/jsp/error/double_posting_error.jsp";
    public static final String PERMISSION_ERROR = "/WEB-INF/jsp/error/permission_error.jsp";
    public static final String CURRENT_VACANCY = "/WEB-INF/jsp/vacancy/vacancy_info.jsp";

    private UrlPattern() {
    }
}
