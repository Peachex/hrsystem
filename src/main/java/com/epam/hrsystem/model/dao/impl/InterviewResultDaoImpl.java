package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.exception.ConnectionPoolException;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.dao.InterviewResultDao;
import com.epam.hrsystem.model.entity.InterviewResult;
import com.epam.hrsystem.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public enum InterviewResultDaoImpl implements InterviewResultDao {
    INSTANCE;
    private static final ConnectionPool pool = ConnectionPool.POOL;

    @Override
    public boolean add(InterviewResult interviewResult) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_INTERVIEW_RESULT)) {
            statement.setByte(1, interviewResult.getRating());
            statement.setString(2, interviewResult.getComment());
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<Long> findInterviewResultId(InterviewResult interviewResult) throws DaoException {
        Optional<Long> id = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_INTERVIEW_RESULT_ID_BY_INTERVIEW_RESULT)) {
            statement.setLong(1, interviewResult.getRating());
            statement.setString(2, interviewResult.getComment());
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
    public Optional<InterviewResult> findInterviewResultById(long interviewResultId) throws DaoException {
        Optional<InterviewResult> interviewResultOptional = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_INTERVIEW_RESULT_BY_ID)) {
            statement.setLong(1, interviewResultId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                InterviewResult interviewResult = createInterviewResultFromResultSet(resultSet);
                interviewResultOptional = Optional.of(interviewResult);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return interviewResultOptional;
    }

    private InterviewResult createInterviewResultFromResultSet(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(1);
        byte rating = resultSet.getByte(2);
        String comment = resultSet.getString(3);
        InterviewResult interviewResult = new InterviewResult(id, rating, comment);
        return interviewResult;
    }
}
