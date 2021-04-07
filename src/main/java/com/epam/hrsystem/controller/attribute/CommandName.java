package com.epam.hrsystem.controller.attribute;

/**
 * Class represents constant command names.
 *
 * @author Aleksey Klevitov
 */
public class CommandName {
    /**
     * Represents command that redirects a user to the list of vacancies.
     */
    public static final String TO_VACANCIES = "/to_vacancies.do";
    /**
     * Represents command that redirects a user to the vacancy info.
     */
    public static final String TO_VACANCY_INFO = "/to_vacancy_info.do?vacancyId=";
    /**
     * Represents command that redirects an employee to the employee's list of vacancies.
     */
    public static final String TO_EMPLOYEE_VACANCIES = "/to_employee_vacancies.do";
    /**
     * Represents command that redirects an employee to the employee's vacancy info.
     */
    public static final String TO_EMPLOYEE_VACANCY_INFO = "/to_employee_vacancy_info.do?vacancyId=";
    /**
     * Represents command that redirects a user to the user profile.
     */
    public static final String TO_USER_PROFILE = "/to_user_profile.do";
    /**
     * Represents command that redirects an applicant to applicant's requests.
     */
    public static final String TO_APPLICANT_REQUESTS = "/to_applicant_requests.do";
    /**
     * Represents command that redirects an employee to applicant requests on certain vacancy.
     */
    public static final String TO_EMPLOYEE_APPLICANT_REQUEST = "/to_employee_applicant_request.do?vacancyId=";
    /**
     * Represents command that redirects an admin to the list of users.
     */
    public static final String TO_ADMIN_USER_LIST = "/to_admin_user_list.do";
    /**
     * Represents command that redirects an admin to the user info.
     */
    public static final String TO_ADMIN_USER_INFO = "/to_admin_user_info.do?userId=";
    /**
     * Represents command that redirects an admin to the list of user reports.
     */
    public static final String TO_ADMIN_USER_REPORT_LIST = "/to_admin_user_report_list.do";
    /**
     * Represents command that redirects an admin to the user report info.
     */
    public static final String TO_ADMIN_USER_REPORT_INFO = "/to_admin_user_report_info.do?reportId=";

    private CommandName() {
    }
}
