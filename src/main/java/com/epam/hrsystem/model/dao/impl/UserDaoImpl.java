package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.exception.ConnectionPoolException;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.dao.SqlQuery;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserRole;
import com.epam.hrsystem.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public enum UserDaoImpl implements UserDao {
    INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.POOL;

    @Override
    public boolean isEmailAvailable(String email) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_SELECT_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            result = !resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<String> findPasswordByEmail(String email) throws DaoException {
        Optional<String> password = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_SELECT_PASSWORD)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = Optional.of(resultSet.getString(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return password;
    }

    @Override
    public boolean add(User user, String password) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_USER)) {
            statement.setString(1, user.getPhotoName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setDate(4, Date.valueOf(user.getDateOfBirth()));
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getEmail());
            statement.setString(7, password);
            statement.setByte(8, user.isActive() ? (byte) 1 : 0);
            statement.setLong(9, findRoleId(user.getRole()).orElseThrow(() -> new DaoException("Invalid role")));
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<Byte> findUserActivity(String email) throws DaoException {
        Optional<Byte> activityValue = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_USER_ACTIVITY_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                activityValue = Optional.of(resultSet.getByte(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return activityValue;
    }

    @Override
    public boolean updateUserActivity(long userId, byte activityValue) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_USER_ACTIVITY)) {
            statement.setByte(1, activityValue);
            statement.setLong(2, userId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean updatePassword(long userId, String newPassword) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setLong(2, userId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users;
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(SqlQuery.SQL_SELECT_ALL_USERS);
            ResultSet resultSet = statement.getResultSet();
            users = createUsersListFromResultSet(resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public List<User> findBlockedUsers() throws DaoException {
        List<User> users;
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(SqlQuery.SQL_SELECT_BLOCKED_USERS);
            ResultSet resultSet = statement.getResultSet();
            users = createUsersListFromResultSet(resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public List<User> findNotBlockedUsers() throws DaoException {
        List<User> users;
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(SqlQuery.SQL_SELECT_NOT_BLOCKED_USERS);
            ResultSet resultSet = statement.getResultSet();
            users = createUsersListFromResultSet(resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public boolean changeUserRole(long userId, UserRole role) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_USER_ROLE)) {
            statement.setLong(1, findRoleId(role).orElseThrow(() -> new DaoException("Invalid role")));
            statement.setLong(2, userId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private Optional<Long> findRoleId(UserRole role) throws DaoException {
        Optional<Long> id = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_ROLE_ID_BY_NAME)) {
            statement.setString(1, role.name());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = Optional.of(resultSet.getLong(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return id;
    }

    private List<User> createUsersListFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        if (resultSet != null && resultSet.next()) {
            do {
                long id = resultSet.getLong(1);
                String photoName = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                LocalDate dateOfBirth = resultSet.getDate(5).toLocalDate();
                String phoneNumber = resultSet.getString(6);
                String email = resultSet.getString(7);
                boolean isActive = resultSet.getByte(8) == 1;
                UserRole role = UserRole.valueOf(resultSet.getString(9).toUpperCase(Locale.ROOT));
                User user = new User(id, role, isActive, photoName, firstName, lastName, dateOfBirth, phoneNumber, email);
                users.add(user);
            } while (resultSet.next());
        } else {
            logger.log(Level.WARN, "Result set is null or empty");
        }
        return users;
    }
}
