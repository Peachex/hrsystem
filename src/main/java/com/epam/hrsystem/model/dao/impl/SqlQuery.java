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

    public static final String SQL_FIND_USER_ACTIVITY_BY_EMAIL = "SELECT is_active FROM users WHERE email = ?;";

    public static final String SQL_UPDATE_USER_ACTIVITY = "UPDATE users SET is_active = ? WHERE user_id = ?;";

    public static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE user_id = ?;";

    public static final String SQL_SELECT_ALL_USERS = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id;";

    public static final String SQL_SELECT_BLOCKED_USERS = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE is_active = '0';";

    public static final String SQL_SELECT_NOT_BLOCKED_USERS = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE is_active = '1';";

    public static final String SQL_UPDATE_USER_ROLE = "UPDATE users SET role_id_fk = ? WHERE user_id = ?;";

    public static final String SQL_UPDATE_USER_INFO = "UPDATE users SET first_name = ?, last_name = ?," +
            " date_of_birth = ?, phone_number = ?, email = ? WHERE user_id = ?;";

    public static final String SQL_FIND_USER_BY_ID = "SELECT user_id, photo_name, first_name, last_name, date_of_birth," +
            " phone_number, email, is_active, role FROM users JOIN user_roles ON role_id_fk = user_role_id WHERE user_id = ?;";

    public static final String SQL_UPDATE_USER_PHOTO = "UPDATE users SET photo_name = ? WHERE user_id = ?";

    // VACANCY QUERIES
    public static final String SQL_INSERT_VACANCY = "INSERT INTO vacancies(is_available, position, description, creation_date," +
            " country_id_fk, city_id_fk, user_id_fk) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_FIND_COUNTRY_ID_BY_NAME = "SELECT country_id FROM countries WHERE country = ?;";

    public static final String SQL_FIND_CITY_ID_BY_NAME = "SELECT city_id FROM cities WHERE city = ?;";

    public static final String SQL_INSERT_COUNTRY = "INSERT INTO countries(country) VALUES (?);";

    public static final String SQL_INSERT_CITY = "INSERT INTO cities(city) VALUES (?);";

    public static final String SQL_SELECT_ALL_VACANCIES = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id;";

    public static final String SQL_UPDATE_VACANCY_AVAILABILITY = "UPDATE vacancies SET is_available = ? WHERE vacancy_id = ?;";

    public static final String SQL_SELECT_DELETED_VACANCIES = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE is_available = '0';";

    public static final String SQL_FIND_VACANCIES_BY_KEY_WORD = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE position LIKE ? OR description LIKE ? OR country LIKE ? OR city LIKE ? HAVING is_available = '1';";

    public static final String SQL_SELECT_AVAILABLE_VACANCIES = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE is_available = '1';";

    public static final String SQL_SELECT_EMPLOYEE_VACANCIES = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE user_id_fk = ?;";

    public static final String SQL_SELECT_ACTIVE_EMPLOYEE_VACANCIES = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE user_id_fk = ? AND is_available = '1';";

    public static final String SQL_SELECT_DELETED_EMPLOYEE_VACANCIES = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE user_id_fk = ? AND is_available = '0';";

    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_WITH_APPLICANTS_REQUESTS = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id JOIN applicant_requests ON vacancy_id = vacancy_id_fk WHERE vacancies.user_id_fk = ?;"; //fixme be careful with applicant_state value

    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_WITH_ACTIVE_APPLICANTS_REQUESTS = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id JOIN applicant_requests ON vacancy_id = vacancy_id_fk WHERE vacancies.user_id_fk = ? AND" +
            " applicant_state != 'PASSED' AND applicant_state != 'FAILED';"; //fixme be careful with applicant_state value

    public static final String SQL_SELECT_EMPLOYEE_VACANCIES_WITH_NOT_ACTIVE_APPLICANTS_REQUESTS = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id JOIN applicant_requests ON vacancy_id = vacancy_id_fk WHERE vacancies.user_id_fk = ? AND" +
            " applicant_state = 'PASSED' OR applicant_state = 'FAILED';"; //fixme be careful with applicant_state value

    public static final String SQL_UPDATE_VACANCY_INFO = "UPDATE vacancies SET position = ?, description = ?," +
            " country_id_fk = ?, city_id_fk = ? WHERE vacancy_id = ?;";

    public static final String SQL_FIND_VACANCY_BY_ID = "SELECT vacancy_id, is_available, position, description, creation_date," +
            " country, city, user_id FROM vacancies JOIN countries ON country_id_fk = country_id JOIN cities ON city_id_fk = city_id" +
            " JOIN users ON user_id_fk = user_id WHERE vacancy_id = ?;";

    public static final String SQL_CHECK_VACANCY_FOR_EXISTENCE = "SELECT vacancy_id FROM vacancies WHERE is_available = '1' AND position = ?" +
            " AND description = ? AND country_id_fk = ? AND city_id_fk = ?;";

    //APPLICANT_REQUEST QUERIES
    public static final String SQL_INSERT_APPLICANT_REQUEST = "INSERT INTO applicant_requests(summary, applicant_state, user_id_fk, vacancy_id_fk)" +
            " VALUES (?, ?, ?, ?);";

    public static final String SQL_CHECK_APPLICANT_REQUEST_FOR_EXISTENCE = "SELECT applicant_request_id FROM applicant_requests WHERE" +
            " user_id_fk = ? AND vacancy_id_fk = ?;";

    private SqlQuery() {
    }
}
