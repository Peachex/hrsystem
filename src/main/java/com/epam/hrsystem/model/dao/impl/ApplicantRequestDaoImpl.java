package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.exception.ConnectionPoolException;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.dao.ApplicantRequestDao;
import com.epam.hrsystem.model.dao.InterviewResultDao;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.dao.VacancyDao;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.ApplicantState;
import com.epam.hrsystem.model.entity.InterviewResult;
import com.epam.hrsystem.model.entity.InterviewType;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ApplicantRequestDao implementation.
 *
 * @author Aleksey Klevitov
 */
public class ApplicantRequestDaoImpl implements ApplicantRequestDao {
    private static final ConnectionPool pool = ConnectionPool.ConnectionPoolHolder.POOL.getConnectionPool();
    private static final InterviewResultDao interviewResultDao = InterviewResultDaoImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile ApplicantRequestDao instance;

    /**
     * Constructs an ApplicantRequestDaoImpl object.
     */
    private ApplicantRequestDaoImpl() {
    }

    /**
     * Returns an ApplicantRequestDao object.
     */
    public static ApplicantRequestDao getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new ApplicantRequestDaoImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean add(ApplicantRequest request) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_APPLICANT_REQUEST)) {
            statement.setString(1, request.getSummary());
            statement.setLong(2, findApplicantStateIdByName(request.getApplicantState().toString()).orElseThrow(() -> new DaoException("Invalid applicant state")));
            statement.setLong(3, request.getApplicant().getId());
            statement.setLong(4, request.getVacancy().getId());
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean applicantRequestExists(ApplicantRequest request) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_CHECK_APPLICANT_REQUEST_FOR_EXISTENCE)) {
            statement.setLong(1, request.getApplicant().getId());
            statement.setLong(2, request.getVacancy().getId());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ApplicantRequest> findApplicantRequestsById(long vacancyId, long applicantId) throws DaoException {
        List<ApplicantRequest> applicantRequests = new ArrayList<>();
        String sqlQuery = vacancyId != 0 ? SqlQuery.SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID : SqlQuery.SQL_SELECT_APPLICANT_REQUESTS_BY_APPLICANT_ID;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            long id = vacancyId != 0 ? vacancyId : applicantId;
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ApplicantRequest applicantRequest = createApplicantRequestFromResultSet(resultSet);
                applicantRequests.add(applicantRequest);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return applicantRequests;
    }

    @Override
    public Optional<ApplicantRequest> findApplicantRequestByVacancyIdAndApplicantId(long vacancyId, long applicantId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID_AND_APPLICANT_ID)) {
            statement.setLong(1, vacancyId);
            statement.setLong(2, applicantId);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(createApplicantRequestFromResultSet(resultSet)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateTechnicalInterviewDate(long applicantRequestId, LocalDate date) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_TECHNICAL_INTERVIEW_DATE_BY_APPLICANT_REQUEST_ID)) {
            statement.setDate(1, Date.valueOf(date));
            statement.setLong(2, applicantRequestId);
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateApplicantState(long applicantRequestId, ApplicantState state) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_APPLICANT_STATE_BY_APPLICANT_REQUEST_ID)) {
            statement.setLong(1, findApplicantStateIdByName(state.name()).orElseThrow(() -> new DaoException("Invalid applicant state")));
            statement.setLong(2, applicantRequestId);
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private Optional<Long> findApplicantStateIdByName(String name) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_APPLICANT_STATE_ID_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getLong(1)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private ApplicantRequest createApplicantRequestFromResultSet(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(1);
        String summary = resultSet.getString(2);

        Date sqlData = resultSet.getDate(3);
        LocalDate technicalInterviewDate = sqlData != null ? sqlData.toLocalDate() : null;

        ApplicantState applicantState = ApplicantState.valueOf(resultSet.getString(4));

        long applicantId = resultSet.getLong(5);
        User applicant = userDao.findUserById(applicantId).orElseThrow(() -> new DaoException("Invalid id"));

        long vacancyId = resultSet.getLong(6);
        Vacancy vacancy = vacancyDao.findVacancyById(vacancyId).orElseThrow(() -> new DaoException("Invalid id"));

        ApplicantRequest applicantRequest = new ApplicantRequest(id, summary, applicantState, applicant, vacancy);
        if (technicalInterviewDate != null) {
            applicantRequest.setTechnicalInterviewDate(technicalInterviewDate);
        }

        List<InterviewResult> interviewResults = interviewResultDao.findInterviewResultsByApplicantRequestId(applicantRequest.getId());
        for (InterviewResult interviewResult : interviewResults) {
            if (interviewResult.getType() == InterviewType.BASIC) {
                applicantRequest.setBasicInterviewResult(interviewResult);
            } else {
                applicantRequest.setTechnicalInterviewResult(interviewResult);
            }
        }
        return applicantRequest;
    }
}
