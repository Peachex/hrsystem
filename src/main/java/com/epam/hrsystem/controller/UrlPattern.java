package com.epam.hrsystem.controller;

public class UrlPattern {
    public static final String CONTROLLER = "*.do";
    public static final String UPLOAD_SERVLET = "*.upload";
    public static final String HOME = "/home";
    public static final String LOGIN = "/login";
    public static final String VACANCY = "/vacancy";
    public static final String REGISTER = "/register";
    public static final String DOUBLE_POSTING_ERROR = "/double_posting_error";
    public static final String PERMISSION_ERROR = "/permission_error";
    public static final String CURRENT_VACANCY = "/vacancy_info";

    private UrlPattern() {
    }
}
