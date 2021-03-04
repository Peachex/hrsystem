package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.exception.ConnectionPoolException;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.dao.ApplicantRequestDao;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.ApplicantState;
import com.epam.hrsystem.model.entity.InterviewResult;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum ApplicantRequestDaoImpl implements ApplicantRequestDao {
    INSTANCE;
    private static final ConnectionPool pool = ConnectionPool.POOL;

    @Override
    public boolean add(ApplicantRequest request) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_APPLICANT_REQUEST)) {
            statement.setString(1, request.getSummary());
            statement.setLong(2, findApplicantStateIdByName(request.getApplicantState().toString()).orElseThrow(() -> new DaoException("Invalid applicant state")));
            statement.setLong(3, request.getApplicant().getId());
            statement.setLong(4, request.getVacancy().getId());
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean applicantRequestExists(ApplicantRequest request) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_CHECK_APPLICANT_REQUEST_FOR_EXISTENCE)) {
            statement.setLong(1, request.getApplicant().getId());
            statement.setLong(2, request.getVacancy().getId());
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<ApplicantRequest> findApplicantRequestsByVacancyId(long vacancyId, String sqlQuery) throws DaoException {
        List<ApplicantRequest> applicantRequests = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setLong(1, vacancyId);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
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
    public List<ApplicantRequest> findApplicantRequestsByApplicantId(long applicantId, String sqlQuery) throws DaoException {
        List<ApplicantRequest> applicantRequests = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setLong(1, applicantId);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
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
        Optional<ApplicantRequest> applicantRequestOptional = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID_AND_APPLICANT_ID)) {
            statement.setLong(1, vacancyId);
            statement.setLong(2, applicantId);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                ApplicantRequest applicantRequest = createApplicantRequestFromResultSet(resultSet);
                applicantRequestOptional = Optional.of(applicantRequest);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return applicantRequestOptional;
    }

    @Override
    public Optional<Long> findApplicantStateIdByName(String name) throws DaoException {
        Optional<Long> id = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_APPLICANT_STATE_ID_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = Optional.of(resultSet.getLong(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return id;
    }

    private ApplicantRequest createApplicantRequestFromResultSet(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(1);
        String summary = resultSet.getString(2);
        ApplicantState applicantState = ApplicantState.valueOf(resultSet.getString(3));
        long applicantId = resultSet.getLong(4);
        User applicant = UserDaoImpl.INSTANCE.findUserById(applicantId).orElseThrow(() -> new DaoException("Invalid id"));
        long vacancyId = resultSet.getLong(5);
        Vacancy vacancy = VacancyDaoImpl.INSTANCE.findVacancyById(vacancyId).orElseThrow(() -> new DaoException("Invalid id"));
        //long basicInterviewResultId = fixme add interview result dao method
        //InterviewResult basicInterviewResult = null;
        //long technicalInterviewResultId =
        //InterviewResult technicalInterviewResult = null;

        InterviewResult basicInterviewResult = new InterviewResult((byte) 10, "Very good student. Very good student. Very good student.");
        InterviewResult technicalInterviewResult = null;// new InterviewResult((byte) 2, "Bad student. Bad student. Bad student.");

        ApplicantRequest applicantRequest = new ApplicantRequest(id, summary, applicantState, applicant, vacancy, basicInterviewResult,
                technicalInterviewResult);
        return applicantRequest;
    }
}
