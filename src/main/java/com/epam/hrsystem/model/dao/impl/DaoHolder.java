package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.model.dao.ApplicantRequestDao;
import com.epam.hrsystem.model.dao.InterviewResultDao;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.dao.UserReportDao;
import com.epam.hrsystem.model.dao.VacancyDao;

/**
 * Enumeration with a single object in it (thread-safe singleton) used to different dao implementations objects manage.
 *
 * @author Aleksey Klevitov
 */
public enum DaoHolder {
    /**
     * Represents a singleton pattern realization.
     */
    HOLDER;
    private final ApplicantRequestDao applicantRequestDao = new ApplicantRequestDaoImpl();
    private final InterviewResultDao interviewResultDao = new InterviewResultDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final VacancyDao vacancyDao = new VacancyDaoImpl();
    private final UserReportDao userReportDao = new UserReportDaoImpl();

    /**
     * Getter method that returns an applicant request dao object.
     *
     * @return ApplicantRequestDao object.
     */
    public ApplicantRequestDao getApplicantRequestDao() {
        return applicantRequestDao;
    }

    /**
     * Getter method that returns an interview result dao object.
     *
     * @return InterviewResultDao object.
     */
    public InterviewResultDao getInterviewResultDao() {
        return interviewResultDao;
    }

    /**
     * Getter method that returns a user dao object.
     *
     * @return UserDao object.
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Getter method that returns a vacancy dao object.
     *
     * @return VacancyDao object.
     */
    public VacancyDao getVacancyDao() {
        return vacancyDao;
    }

    /**
     * Getter method that returns a user report dao object.
     *
     * @return UserReportDao object.
     */
    public UserReportDao getUserReportDao() {
        return userReportDao;
    }
}
