package com.epam.hrsystem.model.dao.impl;

public class SqlQuery {
    //todo delete comments

    // USER QUERIES
    public static final String SQL_SELECT_EMAIL = "SELECT email FROM users WHERE email = ?;";

    public static final String SQL_SELECT_PASSWORD = "SELECT password FROM users WHERE email = ?;";

    public static final String SQL_INSERT_USER = "INSERT INTO users(photo_name, first_name, last_name," +
            "date_of_birth, phone_number, email, password, is_active, role_id_fk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String SQL_FIND_USER_BY_EMAIL = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE email = ?;";

    public static final String SQL_FIND_ROLE_ID_BY_NAME = "SELECT user_role_id FROM user_roles WHERE role = ?;";

    public static final String SQL_FIND_USERS_BY_ACTIVITY = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE is_active = ?;";

    public static final String SQL_FIND_USER_ACTIVITY_BY_EMAIL = "SELECT is_active FROM users WHERE email = ?;";

    public static final String SQL_UPDATE_USER_ACTIVITY = "UPDATE users SET is_active = ? WHERE user_id = ?;";

    public static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE user_id = ?;";

    public static final String SQL_SELECT_ALL_USERS = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id ORDER BY role ASC," +
            "first_name ASC, last_name ASC;";

    public static final String SQL_UPDATE_USER_ROLE = "UPDATE users SET role_id_fk = ? WHERE user_id = ?;";

    public static final String SQL_UPDATE_USER_INFO = "UPDATE users SET first_name = ?, last_name = ?," +
            " date_of_birth = ?, phone_number = ?, email = ? WHERE user_id = ?;";

    public static final String SQL_FIND_USER_BY_ID = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE user_id = ?;";

    public static final String SQL_UPDATE_USER_PHOTO = "UPDATE users SET photo_name = ? WHERE user_id = ?";

    public static final String SQL_FIND_USERS_BY_KEY_WORD = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE first_name LIKE ? OR" +
            " last_name LIKE ? OR email LIKE ? OR phone_number LIKE ?;";


    // VACANCY QUERIES
    public static final String SQL_INSERT_VACANCY = "INSERT INTO vacancies(is_available, position, description, creation_date," +
            " country_id_fk, city_id_fk, user_id_fk) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_FIND_COUNTRY_ID_BY_NAME = "SELECT country_id FROM countries WHERE country = ?;";

    public static final String SQL_FIND_CITY_ID_BY_NAME = "SELECT city_id FROM cities WHERE city = ?;";

    public static final String SQL_INSERT_COUNTRY = "INSERT INTO countries(country) VALUES (?);";

    public static final String SQL_INSERT_CITY = "INSERT INTO cities(city) VALUES (?);";

    public static final String SQL_FIND_VACANCIES_BY_AVAILABILITY = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE is_available = ?;";

    public static final String SQL_UPDATE_VACANCY_AVAILABILITY = "UPDATE vacancies SET is_available = ? WHERE vacancy_id = ?;";

    public static final String SQL_FIND_VACANCIES_BY_KEY_WORD = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE position LIKE ? OR description LIKE ? OR country LIKE ? OR city LIKE ? HAVING is_available = '1';";

    public static final String SQL_SELECT_EMPLOYEE_VACANCIES = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE user_id_fk = ? ORDER BY creation_date DESC, position ASC;";

    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_BY_AVAILABILITY = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id_fk FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " WHERE user_id_fk = ? AND is_available = ? ORDER BY creation_date DESC, position ASC;";

    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_WITH_APPLICANTS_REQUESTS = "SELECT DISTINCT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id JOIN applicant_requests ON vacancy_id = vacancy_id_fk WHERE vacancies.user_id_fk = ? ORDER BY creation_date DESC," +
            " position ASC;";

    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_WITH_ACTIVE_APPLICANTS_REQUESTS = "SELECT DISTINCT vacancy_id, is_available, position, description," +
            " creation_date, country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id JOIN applicant_requests ON vacancy_id = vacancy_id_fk WHERE vacancies.user_id_fk = ? AND EXISTS(SELECT NULL FROM applicant_requests" +
            " WHERE vacancy_id_fk = vacancy_id AND (applicant_state_id_fk = 1 OR applicant_state_id_fk = 2)) ORDER BY creation_date DESC, position ASC;";

    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_WITH_NOT_ACTIVE_APPLICANTS_REQUESTS = "SELECT DISTINCT vacancy_id, is_available, position, description," +
            " creation_date, country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id JOIN applicant_requests ON vacancy_id = vacancy_id_fk WHERE vacancies.user_id_fk = ? AND NOT EXISTS(SELECT NULL FROM applicant_requests" +
            " WHERE vacancy_id_fk = vacancy_id AND (applicant_state_id_fk = 1 OR applicant_state_id_fk = 2)) ORDER BY creation_date DESC, position ASC;";

    public static final String SQL_UPDATE_VACANCY_INFO = "UPDATE vacancies SET position = ?, description = ?," +
            " country_id_fk = ?, city_id_fk = ? WHERE vacancy_id = ?;";

    public static final String SQL_FIND_VACANCY_BY_ID = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE vacancy_id = ?;";

    public static final String SQL_CHECK_VACANCY_FOR_EXISTENCE = "SELECT vacancy_id FROM vacancies WHERE is_available = '1' AND position = ?" +
            " AND description = ? AND country_id_fk = ? AND city_id_fk = ?;";

    //APPLICANT_REQUEST QUERIES
    public static final String SQL_INSERT_APPLICANT_REQUEST = "INSERT INTO applicant_requests(summary, applicant_state_id_fk, user_id_fk, vacancy_id_fk)" +
            " VALUES (?, ?, ?, ?);";

    public static final String SQL_INSERT_INTERVIEW_RESULT = "INSERT INTO interview_results(rating, comment) VALUES (?, ?);";

    public static final String SQL_CHECK_APPLICANT_REQUEST_FOR_EXISTENCE = "SELECT applicant_request_id FROM applicant_requests WHERE" +
            " user_id_fk = ? AND vacancy_id_fk = ?;";

    public static final String SQL_FIND_APPLICANT_STATE_ID_BY_NAME = "SELECT applicant_state_id FROM applicant_states WHERE state = ?;";

    public static final String SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID = "SELECT applicant_request_id, summary, technical_interview_date, state, applicant_requests.user_id_fk, vacancy_id_fk," +
            " basic_interview_result_id_fk, technical_interview_result_id_fk FROM applicant_requests JOIN applicant_states ON applicant_state_id_fk = applicant_state_id" +
            " JOIN vacancies ON vacancy_id_fk = vacancy_id WHERE vacancy_id_fk = ? ORDER BY creation_date DESC;";

    public static final String SQL_SELECT_APPLICANT_REQUESTS_BY_APPLICANT_ID = "SELECT applicant_request_id, summary, technical_interview_date, state, applicant_requests.user_id_fk, vacancy_id_fk," +
            " basic_interview_result_id_fk, technical_interview_result_id_fk FROM applicant_requests JOIN applicant_states ON applicant_state_id_fk = applicant_state_id" +
            " JOIN vacancies ON vacancy_id_fk = vacancy_id WHERE applicant_requests.user_id_fk = ? ORDER BY creation_date DESC;";

    public static final String SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID_AND_APPLICANT_ID = "SELECT applicant_request_id, summary, technical_interview_date, state, applicant_requests.user_id_fk, vacancy_id_fk," +
            " basic_interview_result_id_fk, technical_interview_result_id_fk FROM applicant_requests JOIN applicant_states ON applicant_state_id_fk = applicant_state_id" +
            " WHERE vacancy_id_fk = ? AND user_id_fk = ?;";

    public static final String SQL_UPDATE_APPLICANT_REQUEST_WITH_NULL_TECHNICAL_INTERVIEW = "UPDATE applicant_requests SET summary = ?, technical_interview_date = ?, basic_interview_result_id_fk = ?, applicant_state_id_fk = ?" +
            " WHERE applicant_request_id = ?;";

    public static final String SQL_UPDATE_APPLICANT_REQUEST_WITH_NOT_NULL_TECHNICAL_INTERVIEW = "UPDATE applicant_requests SET summary = ?, technical_interview_date = ?, technical_interview_result_id_fk = ?, applicant_state_id_fk = ?" +
            " WHERE applicant_request_id = ?;";

    public static final String SQL_FIND_INTERVIEW_RESULT_ID_BY_INTERVIEW_RESULT = "SELECT interview_result_id FROM interview_results WHERE rating = ? AND comment = ?;";

    public static final String SQL_FIND_INTERVIEW_RESULT_BY_ID = "SELECT interview_result_id, rating, comment FROM interview_results WHERE interview_result_id = ?;";

    //USER REPORT QUERIES
    public static final String SQL_CHECK_USER_REPORT_FOR_EXISTENCE = "SELECT user_report_id FROM user_reports WHERE is_available = '1' AND subject = ? AND comment = ? AND user_id_fk = ?;";

    public static final String SQL_INSERT_USER_REPORT = "INSERT INTO user_reports(is_available, subject, comment, creation_date, user_id_fk) VALUES (?, ?, ?, ?, ?);";

    public static final String SQL_FIND_USER_REPORT_BY_ID = "SELECT user_report_id, is_available, subject, comment, response, creation_date, user_id_fk FROM user_reports" +
            " WHERE user_report_id = ?;";

    public static final String SQL_UPDATE_USER_REPORT_AVAILABILITY = "UPDATE user_reports SET is_available = ? WHERE user_report_id = ?;";

    public static final String SQL_SELECT_ALL_USER_REPORTS = "SELECT user_report_id, is_available, subject, comment, response, creation_date, user_id_fk FROM user_reports;";

    public static final String SQL_FIND_USER_REPORTS_BY_AVAILABILITY = "SELECT user_report_id, is_available, subject, comment, response, creation_date, user_id_fk" +
            " FROM user_reports WHERE is_available = ?;";

    public static final String SQL_UPDATE_USER_REPORT_RESPONSE = "UPDATE user_reports SET response = ? WHERE user_report_id = ?;";

    public static final String SQL_FIND_USER_REPORTS_BY_KEY_WORD = "SELECT user_report_id, is_available, subject, comment, response," +
            " creation_date, user_id_fk FROM user_reports WHERE subject LIKE ? OR comment LIKE ? OR response LIKE ? HAVING is_available = '1';";

    private SqlQuery() {
    }
}
