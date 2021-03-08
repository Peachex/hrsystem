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
        return applicantRequestDao;
    }

    public InterviewResultDao getInterviewResultDao() {
        return interviewResultDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public VacancyDao getVacancyDao() {
        return vacancyDao;
    }
}
