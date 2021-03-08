package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.model.dao.ApplicantRequestDao;
import com.epam.hrsystem.model.dao.InterviewResultDao;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.dao.VacancyDao;
import com.epam.hrsystem.model.dao.impl.ApplicantRequestDaoImpl;
import com.epam.hrsystem.model.dao.impl.InterviewResultDaoImpl;
import com.epam.hrsystem.model.dao.impl.UserDaoImpl;
import com.epam.hrsystem.model.dao.impl.VacancyDaoImpl;
import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.VacancyService;

public enum ServiceHolder {
    HOLDER;
    private final ApplicantRequestService applicantRequestService = new ApplicantRequestServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final VacancyService vacancyService = new VacancyServiceImpl();

    public ApplicantRequestService getApplicantRequestService() {
        return applicantRequestService;
    }

    public UserService getUserService() {
        return userService;
    }

    public VacancyService getVacancyService() {
        return vacancyService;
    }
}
