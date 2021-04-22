package com.epam.hrsystem.controller.attribute;

/**
 * Class represents constant names of session attributes.
 *
 * @author Aleksey Klevitov
 */
public class SessionAttribute {
    /**
     * Represents logged user's id.
     */
    public static final String USER_ID = "userId";
    /**
     * Represents current locale.
     */
    public static final String CURRENT_LOCALE = "currentLocale";
    /**
     * Represents logged user's role.
     */
    public static final String CURRENT_ROLE = "currentRole";
    /**
     * Represents logged user with all user's fields.
     */
    public static final String USER = "user";
    /**
     * Represents all available vacancies.
     */
    public static final String VACANCIES = "vacancies";
    /**
     * Represents success message.
     */
    public static final String SUCCESS_MESSAGE = "successMessage";

    private SessionAttribute() {
    }
}
