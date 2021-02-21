package com.epam.hrsystem.controller.attribute;

public class PagePath {
    public static final String LOGIN = "/WEB-INF/jsp/authorization/login.jsp";
    public static final String VACANCY_LIST = "/WEB-INF/jsp/vacancy/vacancy_list.jsp";
    public static final String REGISTER = "/WEB-INF/jsp/authorization/register.jsp";
    public static final String PERMISSION_ERROR = "/WEB-INF/jsp/error/permission_error.jsp";
    public static final String CURRENT_VACANCY_INFO = "/WEB-INF/jsp/vacancy/vacancy_info.jsp";
    public static final String EMPLOYEE_CURRENT_VACANCY_INFO = "/WEB-INF/jsp/employee/employee_vacancy_info.jsp";
    public static final String EMPLOYEE_VACANCY_LIST = "/WEB-INF/jsp/employee/employee_vacancy_list.jsp";
    public static final String TO_EMPLOYEE_VACANCY_INFO = "to_employee_vacancy_info";

    private PagePath() {
    }
}
