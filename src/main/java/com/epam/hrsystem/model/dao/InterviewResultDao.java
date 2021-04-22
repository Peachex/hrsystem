package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.InterviewResult;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with interview results table.
 *
 * @author Aleksey Klevitov
 */
public interface InterviewResultDao {
    /**
     * Adds interview result to the table.
     *
     * @param interviewResult    InterviewResult object.
     * @param applicantRequestId long value of applicant request's id.
     * @return boolean value. True if the interview result has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean add(InterviewResult interviewResult, long applicantRequestId) throws DaoException;

    /**
     * Finds interview result id by interview result.
     *
     * @param interviewResult    InterviewResult object.
     * @param applicantRequestId long value of applicant request's id.
     * @return Optional object of long value if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<Long> findInterviewResultId(InterviewResult interviewResult, long applicantRequestId) throws DaoException;

    /**
     * Finds interview results by applicant request id.
     *
     * @param applicantRequestId long value of applicant request's id.
     * @return List object of interview results.
     * @throws DaoException if the database throws SQLException.
     */
    List<InterviewResult> findInterviewResultsByApplicantRequestId(long applicantRequestId) throws DaoException;
}
