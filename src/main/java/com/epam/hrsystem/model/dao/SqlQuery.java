package com.epam.hrsystem.model.dao;

public class SqlQuery {
    // USER QUERIES
    public static final String SQL_SELECT_EMAIL = "SELECT email FROM users WHERE email = ?;";

    public static final String SQL_SELECT_PASSWORD = "SELECT password FROM users WHERE email = ?;";

    public static final String SQL_INSERT_USER = "INSERT INTO users(photo_name, first_name, last_name," +
            "date_of_birth, phone_number, email, password, is_active, role_id_fk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String SQL_FIND_ROLE_ID_BY_NAME = "SELECT user_role_id FROM user_roles WHERE role = ?;";

    public static final String SQL_FIND_USER_ACTIVITY_BY_EMAIL = "SELECT is_active FROM users WHERE email = ?;";

    public static final String SQL_UPDATE_USER_ACTIVITY = "UPDATE users SET is_active = ? WHERE user_id = ?;";

    public static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE user_id = ?;";

    private SqlQuery() {
    }
}
