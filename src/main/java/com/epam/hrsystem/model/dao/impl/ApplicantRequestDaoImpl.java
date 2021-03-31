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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicantRequestDaoImpl implements ApplicantRequestDao {
    private static final ConnectionPool pool = ConnectionPool.ConnectionPoolHolder.POOL.getConnectionPool();

    ApplicantRequestDaoImpl() {
    }

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
    public boolean updateApplicantRequest(ApplicantRequest applicantRequest) throws DaoException {
        boolean result;
        InterviewResult basicInterviewResult = applicantRequest.getBasicInterviewResult();
        InterviewResult technicalInterviewResult = applicantRequest.getTechnicalInterviewResult();
        LocalDate technicalInterviewDate = applicantRequest.getTechnicalInterviewDate();
        String sqlQuery;
        if (basicInterviewResult != null && technicalInterviewResult == null) {
            sqlQuery = SqlQuery.SQL_UPDATE_APPLICANT_REQUEST_WITH_NULL_TECHNICAL_INTERVIEW;
        } else {
            sqlQuery = SqlQuery.SQL_UPDATE_APPLICANT_REQUEST_WITH_NOT_NULL_TECHNICAL_INTERVIEW;
        }
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, applicantRequest.getSummary());
            statement.setDate(2, technicalInterviewDate != null ? Date.valueOf(technicalInterviewDate) : null);
            statement.setLong(3, technicalInterviewResult == null ? DaoHolder.HOLDER.getInterviewResultDao().findInterviewResultId(basicInterviewResult).orElseThrow(() ->
                    new DaoException("Invalid interview result")) : DaoHolder.HOLDER.getInterviewResultDao().findInterviewResultId(technicalInterviewResult).orElseThrow(() ->
                    new DaoException("Invalid interview result")));
            statement.setLong(4, findApplicantStateIdByName(applicantRequest.getApplicantState().name()).orElseThrow(() ->
                    new DaoException("Invalid applicant state")));
            statement.setLong(5, applicantRequest.getId());
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<ApplicantRequest> findApplicantRequestsById(long vacancyId, long applicantId) throws DaoException {
        List<ApplicantRequest> applicantRequests = new ArrayList<>();
        String sqlQuery = vacancyId != 0 ? SqlQuery.SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID :
                SqlQuery.SQL_SELECT_APPLICANT_REQUESTS_BY_APPLICANT_ID;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            long id = vacancyId != 0 ? vacancyId : applicantId;
            statement.setLong(1, id);
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

    private Optional<Long> findApplicantStateIdByName(String name) throws DaoException {
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
        Date sqlData = resultSet.getDate(3);
        LocalDate technicalInterviewDate = sqlData != null ? sqlData.toLocalDate() : null;
        ApplicantState applicantState = ApplicantState.valueOf(resultSet.getString(4));
        long applicantId = resultSet.getLong(5);
        User applicant = DaoHolder.HOLDER.getUserDao().findUserById(applicantId).orElseThrow(() -> new DaoException("Invalid id"));
        long vacancyId = resultSet.getLong(6);
        Vacancy vacancy = DaoHolder.HOLDER.getVacancyDao().findVacancyById(vacancyId).orElseThrow(() -> new DaoException("Invalid id"));
        long basicInterviewResultId = resultSet.getLong(7);
        long technicalInterviewResultId = resultSet.getLong(8);
        ApplicantRequest applicantRequest = new ApplicantRequest(id, summary, applicantState, applicant, vacancy);
        if (technicalInterviewDate != null) {
            applicantRequest.setTechnicalInterviewDate(technicalInterviewDate);
        }
        if (basicInterviewResultId != 0) {
            Optional<InterviewResult> basicInterviewResultOptional = DaoHolder.HOLDER.getInterviewResultDao().findInterviewResultById(basicInterviewResultId);
            basicInterviewResultOptional.ifPresent(applicantRequest::setBasicInterviewResult);
        }
        if (technicalInterviewResultId != 0) {
            Optional<InterviewResult> technicalInterviewResultOptional = DaoHolder.HOLDER.getInterviewResultDao().findInterviewResultById(technicalInterviewResultId);
            technicalInterviewResultOptional.ifPresent(applicantRequest::setTechnicalInterviewResult);
        }
        return applicantRequest;
    }
}
