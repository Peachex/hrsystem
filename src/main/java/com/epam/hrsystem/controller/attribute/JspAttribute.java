package com.epam.hrsystem.controller.attribute;

public class JspAttribute {
    public static final String ERROR_VACANCY_CREATION_ATTRIBUTE = "errorVacancyCreation";
    public static final String ERROR_VACANCY_CREATION_MESSAGE = "Couldn't create vacancy, check input data";

    public static final String ERROR_VACANCY_DELETE_ATTRIBUTE = "errorVacancyDelete";
    public static final String ERROR_VACANCY_DELETE_MESSAGE = "Couldn't delete vacancy, try one more time";
    public static final String ERROR_VACANCY_RESTORE_ATTRIBUTE = "errorVacancyRestore";
    public static final String ERROR_VACANCY_RESTORE_MESSAGE = "Couldn't restore vacancy, try one more time";

    public static final String ERROR_STRANGE_VACANCY_ATTRIBUTE = "strangeVacancy";
    public static final String ERROR_STRANGE_VACANCY_MESSAGE = "This vacancy was created by another employee";

    public static final String ERROR_DUPLICATE_ATTRIBUTE = "errorDuplicate";
    public static final String ERROR_VACANCY_DUPLICATE_MESSAGE = "Couldn't create vacancy cause there is same active vacancy";

    public static final String ERROR_VACANCY_UPDATING_ATTRIBUTE = "errorVacancyUpdate";
    public static final String ERROR_VACANCY_UPDATING_MESSAGE = "Couldn't edit vacancy, try one more time";

    public static final String ERROR_APPLICANT_REQUEST_CREATION_ATTRIBUTE = "errorApplicantRequestCreation";
    public static final String ERROR_APPLICANT_REQUEST_CREATION_MESSAGE = "Couldn't create applicant request, check input data";
    public static final String ERROR_APPLICANT_REQUEST_DUPLICATE_MESSAGE = "Couldn't apply cause you have already applied";

    public static final String ERROR_INPUT_DATA_ATTRIBUTE = "errorInputData";
    public static final String ERROR_INPUT_DATA_MESSAGE = "Input data isn't valid, check fields";

    public static final String NO_VACANCIES_ATTRIBUTE = "noVacancies";
    public static final String NO_VACANCIES_BY_REQUEST_MESSAGE = "No vacancies by your request";
    public static final String NO_VACANCIES_MESSAGE = "No vacancies";

    public static final String NO_APPLICANT_REQUESTS_ATTRIBUTE = "noApplicantRequests";
    public static final String NO_APPLICANT_REQUESTS_MESSAGE = "No applicant requests";

    public static final String NO_APPLICANT_REQUEST_ATTRIBUTE = "noApplicantRequest";
    public static final String NO_APPLICANT_REQUEST_MESSAGE = "No applicant request";

    public static final String NO_VACANCY_ATTRIBUTE = "noVacancy";
    public static final String NO_VACANCY_MESSAGE = "No vacancy";

    public static final String EMAIL_AVAILABLE_ERROR_MESSAGE = "Email is unavailable, try another one!";
    public static final String INVALID_INPUT_DATA_MESSAGE = "";

    public static final String ERROR_MESSAGE_INFO = "errorMessageInfo";

    private JspAttribute() {
    }
}
