package com.epam.hrsystem.controller.attribute;

public class CommandName {
    public static final String TO_VACANCIES = "/to_vacancies.do";
    public static final String TO_VACANCY_INFO = "/to_vacancy_info.do?vacancyId=";
    public static final String TO_EMPLOYEE_VACANCIES = "/to_employee_vacancies.do";
    public static final String TO_EMPLOYEE_VACANCY_INFO = "/to_employee_vacancy_info.do?vacancyId=";
    public static final String TO_USER_PROFILE = "/to_user_profile.do";
    public static final String TO_APPLICANT_REQUESTS = "/to_applicant_requests.do";
    public static final String TO_EMPLOYEE_APPLICANT_REQUEST = "/to_employee_applicant_request.do?vacancyId=";
    public static final String TO_ADMIN_USER_LIST = "/to_admin_user_list.do";
    public static final String TO_ADMIN_USER_INFO = "/to_admin_user_info.do?userId=";
    public static final String TO_ADMIN_USER_REPORT_LIST = "/to_admin_user_report_list.do";
    public static final String TO_ADMIN_USER_REPORT_INFO = "/to_admin_user_report_info.do?reportId=";

    private CommandName() {
    }
}
