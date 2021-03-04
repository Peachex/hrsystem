package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.ApplicantState;
import com.epam.hrsystem.model.entity.InterviewResult;

import java.util.List;
import java.util.Optional;

public interface ApplicantRequestDao {
    boolean add(ApplicantRequest request) throws DaoException;

    boolean applicantRequestExists(ApplicantRequest request) throws DaoException;

    boolean updateApplicantRequest(ApplicantRequest applicantRequest) throws DaoException;

    List<ApplicantRequest> findApplicantRequestsByIdAndSqlQuery(long vacancyId, String sqlQuery) throws DaoException;

    Optional<ApplicantRequest> findApplicantRequestByVacancyIdAndApplicantId(long vacancyId, long applicantId) throws DaoException;

    Optional<Long> findApplicantStateIdByName(String name) throws DaoException;
}
