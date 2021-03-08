package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.model.dao.ApplicantRequestDao;
import com.epam.hrsystem.model.dao.InterviewResultDao;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.dao.VacancyDao;

public enum DaoHolder {
    HOLDER;
    private final ApplicantRequestDao applicantRequestDao = new ApplicantRequestDaoImpl();
    private final InterviewResultDao interviewResultDao = new InterviewResultDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final VacancyDao vacancyDao = new VacancyDaoImpl();

    public ApplicantRequestDao getApplicantRequestDao() {
        return this.applicantRequestDao;
    }

    public InterviewResultDao getInterviewResultDao() {
        return this.interviewResultDao;
    }

    public UserDao getUserDao() {
        return this.userDao;
    }

    public VacancyDao getVacancyDao() {
        return this.vacancyDao;
    }
}
