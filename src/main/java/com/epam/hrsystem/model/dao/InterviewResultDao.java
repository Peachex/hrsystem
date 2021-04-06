package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.InterviewResult;

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
     * @param interviewResult InterviewResult object.
     * @return boolean value. True if the interview result has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean add(InterviewResult interviewResult) throws DaoException;

    /**
     * Finds interview result id by interview result.
     *
     * @param interviewResult InterviewResult object.
     * @return Optional object of long value if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<Long> findInterviewResultId(InterviewResult interviewResult) throws DaoException;

    /**
     * Finds interview result by id.
     *
     * @param interviewResultId InterviewResult object.
     * @return Optional object of interview result if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<InterviewResult> findInterviewResultById(long interviewResultId) throws DaoException;
}
