package com.epam.hrsystem.controller.attribute;

public class CommandName {
    public static final String TO_VACANCY_INFO = "/to_vacancy_info.do?vacancyId=";
    public static final String TO_EMPLOYEE_VACANCIES = "/to_employee_vacancies.do";
    public static final String TO_EMPLOYEE_VACANCY_INFO = "/to_employee_vacancy_info.do?vacancyId=";
    public static final String TO_USER_PROFILE = "/to_user_profile.do";
    public static final String TO_APPLICANT_REQUESTS = "/to_applicant_requests.do";
    public static final String TO_EMPLOYEE_APPLICANT_REQUEST= "/to_employee_applicant_request.do?vacancyId=";

    private CommandName() {
    }
}
