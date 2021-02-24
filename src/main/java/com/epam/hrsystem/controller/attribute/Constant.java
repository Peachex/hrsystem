package com.epam.hrsystem.controller.attribute;

public class Constant {
    public static final String ERROR_VACANCY_CREATION_ATTRIBUTE = "errorVacancyCreation";
    public static final String ERROR_VACANCY_CREATION_MESSAGE = "Couldn't create vacancy, check input data or there is same active" +
            " vacancy";

    public static final String ERROR_VACANCY_DELETE_ATTRIBUTE = "errorVacancyDelete";
    public static final String ERROR_VACANCY_DELETE_MESSAGE = "Couldn't delete vacancy, try one more time";
    public static final String ERROR_VACANCY_RESTORE_ATTRIBUTE = "errorVacancyRestore";
    public static final String ERROR_VACANCY_RESTORE_MESSAGE = "Couldn't restore vacancy, try one more time";

    public static final String ERROR_VACANCY_UPDATING_ATTRIBUTE = "errorVacancyUpdate";
    public static final String ERROR_VACANCY_UPDATING_MESSAGE = "Couldn't edit vacancy, try one more time";

    public static final String ERROR_APPLICANT_REQUEST_CREATION_ATTRIBUTE = "errorApplicantRequestCreation";
    public static final String ERROR_APPLICANT_REQUEST_CREATION_MESSAGE = "Couldn't create applicant request, check input data" +
            " or you have already created request";

    public static final String ERROR_INPUT_DATA_ATTRIBUTE = "errorInputData";
    public static final String ERROR_INPUT_DATA_MESSAGE = "Input data isn't valid, check fields";

    public static final String HR_SYSTEM_MAIL_SUBJECT = "HR-SYSTEM";
    public static final String CREATION_APPLICANT_REQUEST_MAIL_TEXT = "Hello! Thanks for the application you left. Wait for" +
            " telephone interview, our recruiter will contact you. Good luck!";

    public static final String NO_VACANCIES_ATTRIBUTE = "noVacancies";
    public static final String NO_VACANCIES_BY_REQUEST_MESSAGE = "No vacancies by your request";
    public static final String NO_VACANCIES_MESSAGE = "No vacancies";

    public static final String NO_VACANCY_ATTRIBUTE = "noVacancy";
    public static final String NO_VACANCY_MESSAGE = "No vacancy";

    public static final String MAIN_SERVLET_NAME = "Controller";

    public static final String PERCENT_SIGN = "%";

    public static final String SORT_SEQUENCE_ASC = "ASC";

    public static final String EMAIL_AVAILABLE_ERROR_MESSAGE = "Email is unavailable, try another one!";
    public static final String INVALID_INPUT_DATA_MESSAGE = "Invalid input!";

    private Constant() {
    }
}
