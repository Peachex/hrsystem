package com.epam.hrsystem.model.service.impl;

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
