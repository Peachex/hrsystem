package com.epam.hrsystem.model.dao.impl;

/**
 * Represents sql query constants.
 *
 * @author Aleksey Klevitov
 */
public class SqlQuery {
    /**
     * The constant SQL_SELECT_EMAIL.
     */
    public static final String SQL_SELECT_EMAIL = "SELECT email FROM users WHERE email = ?;";

    /**
     * The constant SQL_SELECT_PASSWORD.
     */
    public static final String SQL_SELECT_PASSWORD = "SELECT password FROM users WHERE email = ?;";

    /**
     * The constant SQL_INSERT_USER.
     */
    public static final String SQL_INSERT_USER = "INSERT INTO users(photo_name, first_name, last_name," +
            "date_of_birth, phone_number, email, password, is_active, role_id_fk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * The constant SQL_FIND_USER_BY_EMAIL.
     */
    public static final String SQL_FIND_USER_BY_EMAIL = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE email = ?;";

    /**
     * The constant SQL_FIND_ROLE_ID_BY_NAME.
     */
    public static final String SQL_FIND_ROLE_ID_BY_NAME = "SELECT user_role_id FROM user_roles WHERE role = ?;";

    /**
     * The constant SQL_FIND_USERS_BY_ACTIVITY.
     */
    public static final String SQL_FIND_USERS_BY_ACTIVITY = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE is_active = ?;";

    /**
     * The constant SQL_FIND_USER_ACTIVITY_BY_EMAIL.
     */
    public static final String SQL_FIND_USER_ACTIVITY_BY_EMAIL = "SELECT is_active FROM users WHERE email = ?;";

    /**
     * The constant SQL_UPDATE_USER_ACTIVITY.
     */
    public static final String SQL_UPDATE_USER_ACTIVITY = "UPDATE users SET is_active = ? WHERE user_id = ?;";

    /**
     * The constant SQL_UPDATE_PASSWORD.
     */
    public static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE user_id = ?;";

    /**
     * The constant SQL_SELECT_ALL_USERS.
     */
    public static final String SQL_SELECT_ALL_USERS = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id ORDER BY role ASC," +
            "first_name ASC, last_name ASC;";

    /**
     * The constant SQL_UPDATE_USER_ROLE.
     */
    public static final String SQL_UPDATE_USER_ROLE = "UPDATE users SET role_id_fk = ? WHERE user_id = ?;";

    /**
     * The constant SQL_UPDATE_USER_INFO.
     */
    public static final String SQL_UPDATE_USER_INFO = "UPDATE users SET first_name = ?, last_name = ?," +
            " date_of_birth = ?, phone_number = ?, email = ? WHERE user_id = ?;";

    /**
     * The constant SQL_FIND_USER_BY_ID.
     */
    public static final String SQL_FIND_USER_BY_ID = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE user_id = ?;";

    /**
     * The constant SQL_UPDATE_USER_PHOTO.
     */
    public static final String SQL_UPDATE_USER_PHOTO = "UPDATE users SET photo_name = ? WHERE user_id = ?";

    /**
     * The constant SQL_FIND_USERS_BY_KEY_WORD.
     */
    public static final String SQL_FIND_USERS_BY_KEY_WORD = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE first_name LIKE ? OR" +
            " last_name LIKE ? OR email LIKE ? OR phone_number LIKE ?;";

    /**
     * The constant SQL_INSERT_VACANCY.
     */
    public static final String SQL_INSERT_VACANCY = "INSERT INTO vacancies(is_available, position, description, creation_date," +
            " country_id_fk, city_id_fk, user_id_fk) VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * The constant SQL_FIND_COUNTRY_ID_BY_NAME.
     */
    public static final String SQL_FIND_COUNTRY_ID_BY_NAME = "SELECT country_id FROM countries WHERE country = ?;";

    /**
     * The constant SQL_FIND_CITY_ID_BY_NAME.
     */
    public static final String SQL_FIND_CITY_ID_BY_NAME = "SELECT city_id FROM cities WHERE city = ?;";

    /**
     * The constant SQL_INSERT_COUNTRY.
     */
    public static final String SQL_INSERT_COUNTRY = "INSERT INTO countries(country) VALUES (?);";

    /**
     * The constant SQL_INSERT_CITY.
     */
    public static final String SQL_INSERT_CITY = "INSERT INTO cities(city) VALUES (?);";

    /**
     * The constant SQL_FIND_VACANCIES_BY_AVAILABILITY.
     */
    public static final String SQL_FIND_VACANCIES_BY_AVAILABILITY = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE is_available = ?;";

    /**
     * The constant SQL_UPDATE_VACANCY_AVAILABILITY.
     */
    public static final String SQL_UPDATE_VACANCY_AVAILABILITY = "UPDATE vacancies SET is_available = ? WHERE vacancy_id = ?;";

    /**
     * The constant SQL_FIND_VACANCIES_BY_KEY_WORD.
     */
    public static final String SQL_FIND_VACANCIES_BY_KEY_WORD = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE position LIKE ? OR description LIKE ? OR country LIKE ? OR city LIKE ? HAVING is_available = '1';";

    /**
     * The constant SQL_SELECT_EMPLOYEE_VACANCIES.
     */
    public static final String SQL_SELECT_EMPLOYEE_VACANCIES = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE user_id_fk = ? ORDER BY creation_date DESC, position ASC;";

    /**
     * The constant SQL_SELECT_EMPLOYEE_VACANCIES_BY_AVAILABILITY.
     */
    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_BY_AVAILABILITY = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id_fk FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " WHERE user_id_fk = ? AND is_available = ? ORDER BY creation_date DESC, position ASC;";

    /**
     * The constant SQL_SELECT_EMPLOYEE_VACANCIES_WITH_APPLICANTS_REQUESTS.
     */
    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_WITH_APPLICANTS_REQUESTS = "SELECT DISTINCT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id JOIN applicant_requests ON vacancy_id = vacancy_id_fk WHERE vacancies.user_id_fk = ? ORDER BY creation_date DESC," +
            " position ASC;";

    /**
     * The constant SQL_SELECT_EMPLOYEE_VACANCIES_WITH_ACTIVE_APPLICANTS_REQUESTS.
     */
    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_WITH_ACTIVE_APPLICANTS_REQUESTS = "SELECT DISTINCT vacancy_id, is_available, position, description," +
            " creation_date, country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id JOIN applicant_requests ON vacancy_id = vacancy_id_fk WHERE vacancies.user_id_fk = ? AND EXISTS(SELECT NULL FROM applicant_requests" +
            " JOIN applicant_states ON applicant_state_id_fk = applicant_state_id WHERE vacancy_id_fk = vacancy_id AND (state = 'LEFT_REQUEST' OR state = 'READY_FOR_TECHNICAL_INTERVIEW'))" +
            " ORDER BY creation_date DESC, position ASC;";

    /**
     * The constant SQL_SELECT_EMPLOYEE_VACANCIES_WITH_NOT_ACTIVE_APPLICANTS_REQUESTS.
     */
    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_WITH_NOT_ACTIVE_APPLICANTS_REQUESTS = "SELECT DISTINCT vacancy_id, is_available, position, description," +
            " creation_date, country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id JOIN applicant_requests ON vacancy_id = vacancy_id_fk WHERE vacancies.user_id_fk = ? AND NOT EXISTS(SELECT NULL FROM applicant_requests" +
            " JOIN applicant_states ON applicant_state_id_fk = applicant_state_id WHERE vacancy_id_fk = vacancy_id AND (state = 'LEFT_REQUEST' OR state = 'READY_FOR_TECHNICAL_INTERVIEW'))" +
            " ORDER BY creation_date DESC, position ASC;";

    /**
     * The constant SQL_UPDATE_VACANCY_INFO.
     */
    public static final String SQL_UPDATE_VACANCY_INFO = "UPDATE vacancies SET position = ?, description = ?," +
            " country_id_fk = ?, city_id_fk = ? WHERE vacancy_id = ?;";

    /**
     * The constant SQL_FIND_VACANCY_BY_ID.
     */
    public static final String SQL_FIND_VACANCY_BY_ID = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE vacancy_id = ?;";

    /**
     * The constant SQL_CHECK_VACANCY_FOR_EXISTENCE.
     */
    public static final String SQL_CHECK_VACANCY_FOR_EXISTENCE = "SELECT vacancy_id FROM vacancies WHERE is_available = '1' AND position = ?" +
            " AND description = ? AND country_id_fk = ? AND city_id_fk = ?;";

    /**
     * The constant SQL_INSERT_APPLICANT_REQUEST.
     */
    public static final String SQL_INSERT_APPLICANT_REQUEST = "INSERT INTO applicant_requests(summary, applicant_state_id_fk, user_id_fk, vacancy_id_fk)" +
            " VALUES (?, ?, ?, ?);";

    /**
     * The constant SQL_INSERT_INTERVIEW_RESULT.
     */
    public static final String SQL_INSERT_INTERVIEW_RESULT = "INSERT INTO interview_results(rating, comment, interview_type_id_fk," +
            " applicant_request_id_fk) VALUES (?, ?, ?, ?);";

    /**
     * The constant SQL_FIND_INTERVIEW_TYPE_ID_BY_TYPE.
     */
    public static final String SQL_FIND_INTERVIEW_TYPE_ID_BY_TYPE = "SELECT interview_type_id FROM interview_types" +
            " WHERE type = ?;";

    /**
     * The constant SQL_CHECK_APPLICANT_REQUEST_FOR_EXISTENCE.
     */
    public static final String SQL_CHECK_APPLICANT_REQUEST_FOR_EXISTENCE = "SELECT applicant_request_id FROM applicant_requests WHERE" +
            " user_id_fk = ? AND vacancy_id_fk = ?;";

    /**
     * The constant SQL_FIND_APPLICANT_STATE_ID_BY_NAME.
     */
    public static final String SQL_FIND_APPLICANT_STATE_ID_BY_NAME = "SELECT applicant_state_id FROM applicant_states WHERE state = ?;";

    /**
     * The constant SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID.
     */
    public static final String SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID = "SELECT applicant_request_id, summary, technical_interview_date, state, applicant_requests.user_id_fk, vacancy_id_fk" +
            " FROM applicant_requests JOIN applicant_states ON applicant_state_id_fk = applicant_state_id" +
            " JOIN vacancies ON vacancy_id_fk = vacancy_id WHERE vacancy_id_fk = ? ORDER BY creation_date DESC;";

    /**
     * The constant SQL_SELECT_APPLICANT_REQUESTS_BY_APPLICANT_ID.
     */
    public static final String SQL_SELECT_APPLICANT_REQUESTS_BY_APPLICANT_ID = "SELECT applicant_request_id, summary, technical_interview_date, state, applicant_requests.user_id_fk, vacancy_id_fk" +
            " FROM applicant_requests JOIN applicant_states ON applicant_state_id_fk = applicant_state_id JOIN vacancies ON vacancy_id_fk = vacancy_id WHERE applicant_requests.user_id_fk = ?" +
            " ORDER BY creation_date DESC;";

    /**
     * The constant SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID_AND_APPLICANT_ID.
     */
    public static final String SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID_AND_APPLICANT_ID = "SELECT applicant_request_id, summary, technical_interview_date, state, applicant_requests.user_id_fk, vacancy_id_fk" +
            " FROM applicant_requests JOIN applicant_states ON applicant_state_id_fk = applicant_state_id" +
            " WHERE vacancy_id_fk = ? AND user_id_fk = ?;";

    /**
     * The constant SQL_UPDATE_TECHNICAL_INTERVIEW_DATE_BY_APPLICANT_REQUEST_ID.
     */
    public static final String SQL_UPDATE_TECHNICAL_INTERVIEW_DATE_BY_APPLICANT_REQUEST_ID = "UPDATE applicant_requests SET technical_interview_date = ? WHERE applicant_request_id = ?";

    /**
     * The constant SQL_UPDATE_APPLICANT_STATE_BY_APPLICANT_REQUEST_ID.
     */
    public static final String SQL_UPDATE_APPLICANT_STATE_BY_APPLICANT_REQUEST_ID = "UPDATE applicant_requests SET applicant_state_id_fk = ? WHERE applicant_request_id = ?";

    /**
     * The constant SQL_FIND_INTERVIEW_RESULT_ID_BY_INTERVIEW_RESULT.
     */
    public static final String SQL_FIND_INTERVIEW_RESULT_ID_BY_INTERVIEW_RESULT = "SELECT interview_result_id FROM interview_results LEFT JOIN interview_types ON interview_type_id_fk = interview_type_id WHERE rating = ? AND" +
            " comment = ? AND interview_type_id_fk = ? AND applicant_request_id_fk = ?;";

    /**
     * The constant SQL_FIND_INTERVIEW_RESULT_BY_ID.
     */
    public static final String SQL_FIND_INTERVIEW_RESULT_BY_APPLICANT_REQUEST_ID = "SELECT interview_result_id, rating, comment, type FROM interview_results LEFT JOIN interview_types ON interview_type_id_fk = interview_type_id WHERE" +
            " applicant_request_id_fk = ?;";

    /**
     * The constant SQL_CHECK_USER_REPORT_FOR_EXISTENCE.
     */
    public static final String SQL_CHECK_USER_REPORT_FOR_EXISTENCE = "SELECT user_report_id FROM user_reports WHERE is_available = '1' AND subject = ? AND comment = ? AND user_id_fk = ?;";

    /**
     * The constant SQL_INSERT_USER_REPORT.
     */
    public static final String SQL_INSERT_USER_REPORT = "INSERT INTO user_reports(is_available, subject, comment, creation_date, user_id_fk) VALUES (?, ?, ?, ?, ?);";

    /**
     * The constant SQL_FIND_USER_REPORT_BY_ID.
     */
    public static final String SQL_FIND_USER_REPORT_BY_ID = "SELECT user_report_id, is_available, subject, comment, response, creation_date, user_id_fk FROM user_reports" +
            " WHERE user_report_id = ?;";

    /**
     * The constant SQL_SELECT_ALL_USER_REPORTS.
     */
    public static final String SQL_SELECT_ALL_USER_REPORTS = "SELECT user_report_id, is_available, subject, comment, response, creation_date, user_id_fk FROM user_reports" +
            " ORDER BY creation_date DESC;";

    /**
     * The constant SQL_FIND_USER_REPORTS_BY_AVAILABILITY.
     */
    public static final String SQL_FIND_USER_REPORTS_BY_AVAILABILITY = "SELECT user_report_id, is_available, subject, comment, response, creation_date, user_id_fk" +
            " FROM user_reports WHERE is_available = ? ORDER BY creation_date DESC;";

    /**
     * The constant SQL_UPDATE_USER_REPORT_RESPONSE.
     */
    public static final String SQL_UPDATE_USER_REPORT_RESPONSE = "UPDATE user_reports SET response = ?, is_available = 0 WHERE user_report_id = ?;";

    /**
     * The constant SQL_FIND_USER_REPORTS_BY_KEY_WORD.
     */
    public static final String SQL_FIND_USER_REPORTS_BY_KEY_WORD = "SELECT user_report_id, is_available, subject, comment, response," +
            " creation_date, user_id_fk FROM user_reports JOIN users ON user_id_fk = user_id WHERE subject LIKE ? OR comment LIKE ? OR response LIKE ? OR email LIKE ?" +
            " HAVING is_available = '1';";

    private SqlQuery() {
    }
}
