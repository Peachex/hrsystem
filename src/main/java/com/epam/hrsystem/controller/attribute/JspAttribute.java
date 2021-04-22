package com.epam.hrsystem.controller.attribute;

/**
 * Class represents constant names of jsp attributes.
 *
 * @author Aleksey Klevitov
 */
public class JspAttribute {
    /**
     * The constant that represents ERROR_VACANCY_CREATION_ATTRIBUTE.
     */
    public static final String ERROR_VACANCY_CREATION_ATTRIBUTE = "errorVacancyCreation";
    /**
     * The constant that represents ERROR_VACANCY_CREATION_MESSAGE.
     */
    public static final String ERROR_VACANCY_CREATION_MESSAGE = "Couldn't create vacancy, check input data";

    /**
     * The constant that represents ERROR_VACANCY_DELETE_ATTRIBUTE.
     */
    public static final String ERROR_VACANCY_DELETE_ATTRIBUTE = "errorVacancyDelete";
    /**
     * The constant that represents ERROR_VACANCY_DELETE_MESSAGE.
     */
    public static final String ERROR_VACANCY_DELETE_MESSAGE = "Couldn't delete vacancy";
    /**
     * The constant that represents ERROR_VACANCY_RESTORE_ATTRIBUTE.
     */
    public static final String ERROR_VACANCY_RESTORE_ATTRIBUTE = "errorVacancyRestore";
    /**
     * The constant that represents ERROR_VACANCY_RESTORE_MESSAGE.
     */
    public static final String ERROR_VACANCY_RESTORE_MESSAGE = "Couldn't restore vacancy";

    /**
     * The constant that represents ERROR_STRANGE_VACANCY_ATTRIBUTE.
     */
    public static final String ERROR_STRANGE_VACANCY_ATTRIBUTE = "strangeVacancy";
    /**
     * The constant that represents ERROR_STRANGE_VACANCY_MESSAGE.
     */
    public static final String ERROR_STRANGE_VACANCY_MESSAGE = "This vacancy was created by another employee";

    /**
     * The constant that represents ERROR_DUPLICATE_ATTRIBUTE.
     */
    public static final String ERROR_DUPLICATE_ATTRIBUTE = "errorDuplicate";
    /**
     * The constant that represents ERROR_VACANCY_DUPLICATE_MESSAGE.
     */
    public static final String ERROR_VACANCY_DUPLICATE_MESSAGE = "Couldn't create vacancy cause there is same active vacancy";

    /**
     * The constant that represents ERROR_VACANCY_UPDATING_ATTRIBUTE.
     */
    public static final String ERROR_VACANCY_UPDATING_ATTRIBUTE = "errorVacancyUpdate";
    /**
     * The constant that represents ERROR_VACANCY_UPDATING_MESSAGE.
     */
    public static final String ERROR_VACANCY_UPDATING_MESSAGE = "Couldn't edit vacancy, there is same active vacancy";

    /**
     * The constant that represents ERROR_APPLICANT_REQUEST_CREATION_ATTRIBUTE.
     */
    public static final String ERROR_APPLICANT_REQUEST_CREATION_ATTRIBUTE = "errorApplicantRequestCreation";
    /**
     * The constant that represents ERROR_APPLICANT_REQUEST_CREATION_MESSAGE.
     */
    public static final String ERROR_APPLICANT_REQUEST_CREATION_MESSAGE = "Couldn't create applicant request, check input data";
    /**
     * The constant that represents ERROR_APPLICANT_REQUEST_DUPLICATE_MESSAGE.
     */
    public static final String ERROR_APPLICANT_REQUEST_DUPLICATE_MESSAGE = "Couldn't apply cause you have already applied";

    /**
     * The constant that represents ERROR_INPUT_DATA_ATTRIBUTE.
     */
    public static final String ERROR_INPUT_DATA_ATTRIBUTE = "errorInputData";
    /**
     * The constant that represents ERROR_INPUT_DATA_MESSAGE.
     */
    public static final String ERROR_INPUT_DATA_MESSAGE = "Input data isn't valid, check fields";

    /**
     * The constant that represents ERROR_USER_BLOCKING_ATTRIBUTE.
     */
    public static final String ERROR_USER_BLOCKING_ATTRIBUTE = "errorUserBlocking";
    /**
     * The constant that represents ERROR_USER_BLOCKING_MESSAGE.
     */
    public static final String ERROR_USER_BLOCKING_MESSAGE = "Couldn't block user";

    /**
     * The constant that represents NO_VACANCIES_ATTRIBUTE.
     */
    public static final String NO_VACANCIES_ATTRIBUTE = "noVacancies";
    /**
     * The constant that represents NO_VACANCIES_BY_REQUEST_MESSAGE.
     */
    public static final String NO_VACANCIES_BY_REQUEST_MESSAGE = "No vacancies by your request";
    /**
     * The constant that represents NO_VACANCIES_MESSAGE.
     */
    public static final String NO_VACANCIES_MESSAGE = "No vacancies";

    /**
     * The constant that represents NO_APPLICANT_REQUESTS_ATTRIBUTE.
     */
    public static final String NO_APPLICANT_REQUESTS_ATTRIBUTE = "noApplicantRequests";
    /**
     * The constant that represents NO_APPLICANT_REQUESTS_MESSAGE.
     */
    public static final String NO_APPLICANT_REQUESTS_MESSAGE = "No applicant requests";

    /**
     * The constant that represents NO_APPLICANT_REQUEST_ATTRIBUTE.
     */
    public static final String NO_APPLICANT_REQUEST_ATTRIBUTE = "noApplicantRequest";
    /**
     * The constant that represents NO_APPLICANT_REQUEST_MESSAGE.
     */
    public static final String NO_APPLICANT_REQUEST_MESSAGE = "No applicant request";

    /**
     * The constant that represents NO_VACANCY_ATTRIBUTE.
     */
    public static final String NO_VACANCY_ATTRIBUTE = "noVacancy";
    /**
     * The constant that represents NO_VACANCY_MESSAGE.
     */
    public static final String NO_VACANCY_MESSAGE = "No vacancy";

    /**
     * The constant that represents ERROR_INTERVIEW_RESULT_CREATION_ATTRIBUTE.
     */
    public static final String ERROR_INTERVIEW_RESULT_CREATION_ATTRIBUTE = "errorInterviewResultCreation";
    /**
     * The constant that represents ERROR_INTERVIEW_RESULT_DUPLICATE_CREATION_MESSAGE.
     */
    public static final String ERROR_INTERVIEW_RESULT_DUPLICATE_CREATION_MESSAGE = "Couldn't create interview result cause you have" +
            " already created";

    /**
     * The constant that represents ERROR_TECHNICAL_INTERVIEW_SCHEDULING_ATTRIBUTE.
     */
    public static final String ERROR_TECHNICAL_INTERVIEW_SCHEDULING_ATTRIBUTE = "errorTechnicalInterviewScheduling";
    /**
     * The constant that represents ERROR_TECHNICAL_INTERVIEW_SCHEDULING_MESSAGE.
     */
    public static final String ERROR_TECHNICAL_INTERVIEW_SCHEDULING_MESSAGE = "Couldn't schedule interview result cause you have" +
            " already scheduled";

    /**
     * The constant that represents ERROR_INVALID_CURRENT_PASSWORD_ATTRIBUTE.
     */
    public static final String ERROR_INVALID_CURRENT_PASSWORD_ATTRIBUTE = "invalidCurrentPassword";
    /**
     * The constant that represents ERROR_INVALID_CURRENT_PASSWORD_MESSAGE.
     */
    public static final String ERROR_INVALID_CURRENT_PASSWORD_MESSAGE = "Invalid current password";

    /**
     * The constant that represents USER_ACCOUNT_IS_DELETED_ATTRIBUTE.
     */
    public static final String USER_ACCOUNT_IS_DELETED_ATTRIBUTE = "accountIsDeleted";
    /**
     * The constant that represents USER_ACCOUNT_IS_DELETED_MESSAGE.
     */
    public static final String USER_ACCOUNT_IS_DELETED_MESSAGE = "Couldn't login cause your account was deleted";

    /**
     * The constant that represents NO_USER_ATTRIBUTE.
     */
    public static final String NO_USER_ATTRIBUTE = "noUser";
    /**
     * The constant that represents NO_USER_MESSAGE.
     */
    public static final String NO_USER_MESSAGE = "No user";
    /**
     * The constant that represents NO_USERS_ATTRIBUTE.
     */
    public static final String NO_USERS_ATTRIBUTE = "noUsers";
    /**
     * The constant that represents NO_USERS_MESSAGE.
     */
    public static final String NO_USERS_MESSAGE = "No users";
    /**
     * The constant that represents NO_USERS_BY_REQUEST_MESSAGE.
     */
    public static final String NO_USERS_BY_REQUEST_MESSAGE = "No users by your request";

    /**
     * The constant that represents NO_REPORT_ATTRIBUTE.
     */
    public static final String NO_REPORT_ATTRIBUTE = "noReport";
    /**
     * The constant that represents NO_REPORT_MESSAGE.
     */
    public static final String NO_REPORT_MESSAGE = "No report";
    /**
     * The constant that represents NO_REPORTS_ATTRIBUTE.
     */
    public static final String NO_REPORTS_ATTRIBUTE = "noReports";
    /**
     * The constant that represents NO_REPORTS_MESSAGE.
     */
    public static final String NO_REPORTS_MESSAGE = "No reports";
    /**
     * The constant that represents NO_REPORTS_BY_REQUEST_MESSAGE.
     */
    public static final String NO_REPORTS_BY_REQUEST_MESSAGE = "No reports by your request";

    /**
     * The constant that represents EMAIL_AVAILABLE_ERROR_MESSAGE.
     */
    public static final String EMAIL_AVAILABLE_ERROR_MESSAGE = "Email is unavailable, try another one!";
    /**
     * The constant that represents INVALID_INPUT_DATA_MESSAGE.
     */
    public static final String INVALID_INPUT_DATA_MESSAGE = "";

    /**
     * The constant that represents ERROR_USER_REPORT_DUPLICATE_MESSAGE.
     */
    public static final String ERROR_USER_REPORT_DUPLICATE_MESSAGE = "Couldn't create report cause there is same active report";

    /**
     * The constant that represents ERROR_MESSAGE_INFO.
     */
    public static final String ERROR_MESSAGE_INFO = "errorMessageInfo";

    /**
     * The constant that represents SUCCESS_ATTRIBUTE.
     */
    public static final String SUCCESS_ATTRIBUTE = "successMessage";

    /**
     * The constant that represents SUCCESS_MESSAGE.
     */
    public static final String SUCCESS_MESSAGE = "Successfully completed / успешно выполнено";

    private JspAttribute() {
    }
}
