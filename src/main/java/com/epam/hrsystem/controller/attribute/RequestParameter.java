package com.epam.hrsystem.controller.attribute;

public class RequestParameter {

    // User
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String DATE_OF_BIRTH = "dateOfBirth";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String REPEATED_PASSWORD = "repeatedPassword";
    public static final String CURRENT_PASSWORD = "currentPassword";
    public static final String NEW_PASSWORD = "newPassword";
    public static final String REPEATED_NEW_PASSWORD = "repeatedNewPassword";
    public static final String USERS = "users";

    //Vacancy
    public static final String POSITION = "position";
    public static final String DESCRIPTION = "description";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String VACANCIES = "vacancies";
    public static final String VACANCY_ID = "vacancyId";
    public static final String KEY_WORD = "keyWord";
    public static final String SORT_SEQUENCE = "sortSequence";
    public static final String VACANCY = "vacancy";
    public static final String EMPLOYEE_VACANCIES = "employeeVacancies";

    //Vacancy request
    public static final String SUMMARY = "summary";

    //Applicant requests
    public static final String APPLICANT_REQUESTS = "applicantRequests";
    public static final String APPLICANT_REQUEST = "applicantRequest";
    public static final String APPLICANT_ID = "applicantId";
    public static final String INTERVIEW_RESULT_RATING = "rating";
    public static final String INTERVIEW_RESULT_COMMENT = "comment";
    public static final String APPLICANT_STATE = "applicantState";
    public static final String TECHNICAL_INTERVIEW_DATE = "technicalInterviewDate";

    //Different
    public static final String NEW_LOCALE = "newLocale";

    public static final String HEADER_REFERER = "REFERER";

    public static final String FILE_UPLOADING = "file";
    public static final String FILE_NAME = "fileName";

    private RequestParameter() {
    }

}
