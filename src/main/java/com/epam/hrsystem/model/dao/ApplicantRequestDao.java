package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.ApplicantState;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with applicant requests table.
 *
 * @author Aleksey Klevitov
 */
public interface ApplicantRequestDao {
    /**
     * Adds applicant request to the table.
     *
     * @param request ApplicantRequest object.
     * @return boolean value. True if the applicant request has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean add(ApplicantRequest request) throws DaoException;

    /**
     * Checks if applicant request exists.
     *
     * @param request ApplicantRequest object.
     * @return boolean value. True if the applicant request exists, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean applicantRequestExists(ApplicantRequest request) throws DaoException;

    /**
     * Finds applicant requests by id.
     *
     * @param vacancyId   long value of vacancy's id.
     * @param applicantId long value of applicant's id.
     * @return List object of applicants requests.
     * @throws DaoException if the database throws SQLException.
     */
    List<ApplicantRequest> findApplicantRequestsById(long vacancyId, long applicantId) throws DaoException;

    /**
     * Finds applicant request by vacancy id and applicant id.
     *
     * @param vacancyId   long value of vacancy's id.
     * @param applicantId long value of applicant's id.
     * @return Optional object of applicant request if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<ApplicantRequest> findApplicantRequestByVacancyIdAndApplicantId(long vacancyId, long applicantId) throws DaoException;

    /**
     * Updates technical interview date.
     *
     * @param applicantRequestId long value of applicant request's id.
     * @param date               LocalDate object of technical interview date.
     * @return boolean value. True if the technical interview date was updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateTechnicalInterviewDate(long applicantRequestId, LocalDate date) throws DaoException;

    /**
     * Updates applicant state.
     *
     * @param applicantRequestId long value of applicant request's id.
     * @param state              ApplicantState object that represents applicant state.
     * @return boolean value. True if the applicant state was updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateApplicantState(long applicantRequestId, ApplicantState state) throws DaoException;
}
