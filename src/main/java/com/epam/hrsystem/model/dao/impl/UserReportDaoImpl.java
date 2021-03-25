package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.exception.ConnectionPoolException;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.dao.UserReportDao;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserReport;
import com.epam.hrsystem.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserReportDaoImpl implements UserReportDao {
    private static final ConnectionPool pool = ConnectionPool.ConnectionPoolHolder.POOL.getConnectionPool();

    UserReportDaoImpl() {
    }

    @Override
    public boolean add(UserReport report) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_USER_REPORT)) {
            statement.setByte(1, report.getIsAvailable() ? (byte) 1 : 0);
            statement.setString(2, report.getSubject());
            statement.setString(3, report.getComment());
            statement.setDate(4, Date.valueOf(report.getCreationDate()));
            statement.setLong(5, report.getUser().getId());
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<UserReport> findUserReportById(long reportId) throws DaoException {
        Optional<UserReport> report = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_USER_REPORT_BY_ID)) {
            statement.setLong(1, reportId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                report = Optional.of(createUserReportFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return report;
    }

    @Override
    public boolean updateUserReportAvailability(long reportId, byte availabilityValue) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_USER_REPORT_AVAILABILITY)) {
            statement.setByte(1, availabilityValue);
            statement.setLong(2, reportId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<UserReport> findAllUserReports() throws DaoException {
        List<UserReport> reports = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(SqlQuery.SQL_SELECT_ALL_USER_REPORTS);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                UserReport report = createUserReportFromResultSet(resultSet);
                reports.add(report);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return reports;
    }

    @Override
    public List<UserReport> findUserReportsByAvailability(boolean areAvailable) throws DaoException {
        List<UserReport> reports = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_USER_REPORTS_BY_AVAILABILITY)) {
            statement.setByte(1, areAvailable ? (byte) 1 : 0);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserReport report = createUserReportFromResultSet(resultSet);
                reports.add(report);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return reports;
    }

    @Override
    public List<UserReport> findUserReportsByKeyWord(String keyWord) throws DaoException {
        List<UserReport> reports = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_USER_REPORTS_BY_KEY_WORD)) {
            statement.setString(1, keyWord);
            statement.setString(2, keyWord);
            statement.setString(3, keyWord);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                UserReport report = createUserReportFromResultSet(resultSet);
                reports.add(report);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return reports;
    }

    @Override
    public boolean updateUserReportResponse(UserReport report) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_USER_REPORT_RESPONSE)) {
            statement.setString(1, report.getResponse());
            statement.setLong(2, report.getId());
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean userReportExists(UserReport report) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_CHECK_USER_REPORT_FOR_EXISTENCE)) {
            statement.setString(1, report.getSubject());
            statement.setString(2, report.getComment());
            statement.setLong(3, report.getUser().getId());
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private UserReport createUserReportFromResultSet(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(1);
        boolean isAvailable = resultSet.getByte(2) == 1;
        String subject = resultSet.getString(3);
        String comment = resultSet.getString(4);
        String response = resultSet.getString(5);
        LocalDate creationDate = resultSet.getDate(6).toLocalDate();
        long userId = resultSet.getLong(7);
        User user = DaoHolder.HOLDER.getUserDao().findUserById(userId).orElseThrow(() -> new DaoException("Invalid id"));
        UserReport report = new UserReport(id, isAvailable, subject, comment, creationDate, user);
        if (response != null) {
            report.setResponse(response);
        }
        return report;
    }
}
