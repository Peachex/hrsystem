package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.exception.ConnectionPoolException;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.dao.SqlQuery;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserRole;
import com.epam.hrsystem.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public enum UserDaoImpl implements UserDao {
    INSTANCE;
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
    public boolean updateUserActivity(long id, byte activityValue) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_USER_ACTIVITY)) {
            statement.setByte(1, activityValue);
            statement.setLong(2, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean updatePassword(long id, String newPassword) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setLong(2, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
