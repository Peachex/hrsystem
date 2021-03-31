package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.InterviewResult;

import java.util.Optional;

public interface InterviewResultDao {
    boolean add(InterviewResult interviewResult) throws DaoException;

    Optional<Long> findInterviewResultId(InterviewResult interviewResult) throws DaoException;

    Optional<InterviewResult> findInterviewResultById(long interviewResultId) throws DaoException;
}
