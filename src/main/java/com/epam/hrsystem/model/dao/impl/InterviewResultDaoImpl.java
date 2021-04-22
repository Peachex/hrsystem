package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.exception.ConnectionPoolException;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.dao.InterviewResultDao;
import com.epam.hrsystem.model.entity.InterviewResult;
import com.epam.hrsystem.model.entity.InterviewType;
import com.epam.hrsystem.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * InterviewResultDao implementation.
 *
 * @author Aleksey Klevitov
 */
public class InterviewResultDaoImpl implements InterviewResultDao {
    private static final ConnectionPool pool = ConnectionPool.ConnectionPoolHolder.POOL.getConnectionPool();
    private static final Lock locker = new ReentrantLock();
    private static volatile InterviewResultDao instance;

    /**
     * Constructs an InterviewResultDaoImpl object.
     */
    private InterviewResultDaoImpl() {
    }

    /**
     * Returns an InterviewResultDao object.
     */
    public static InterviewResultDao getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new InterviewResultDaoImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean add(InterviewResult interviewResult, long applicantRequestId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_INTERVIEW_RESULT)) {
            statement.setByte(1, interviewResult.getRating());
            statement.setString(2, interviewResult.getComment());
            statement.setLong(3, findInterviewTypeIdByType(interviewResult.getType()).orElseThrow(() -> new DaoException("Invalid interview type")));
            statement.setLong(4, applicantRequestId);
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Long> findInterviewResultId(InterviewResult interviewResult, long applicantRequestId) throws DaoException {
        Optional<Long> id = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_INTERVIEW_RESULT_ID_BY_INTERVIEW_RESULT)) {
            statement.setLong(1, interviewResult.getRating());
            statement.setString(2, interviewResult.getComment());
            statement.setLong(3, findInterviewTypeIdByType(interviewResult.getType()).orElseThrow(() -> new DaoException("Invalid interview type")));
            statement.setLong(4, applicantRequestId);
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
    public List<InterviewResult> findInterviewResultsByApplicantRequestId(long applicantRequestId) throws DaoException {
        List<InterviewResult> interviewResults = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_INTERVIEW_RESULT_BY_APPLICANT_REQUEST_ID)) {
            statement.setLong(1, applicantRequestId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                interviewResults.add(createInterviewResultFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return interviewResults;
    }

    private Optional<Long> findInterviewTypeIdByType(InterviewType type) throws DaoException {
        Optional<Long> id = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_INTERVIEW_TYPE_ID_BY_TYPE)) {
            statement.setString(1, type.name());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = Optional.of(resultSet.getLong(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return id;
    }

    private InterviewResult createInterviewResultFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        byte rating = resultSet.getByte(2);
        String comment = resultSet.getString(3);
        InterviewType type = InterviewType.valueOf(resultSet.getString(4).toUpperCase(Locale.ROOT));
        return (new InterviewResult(id, rating, comment, type));
    }
}
