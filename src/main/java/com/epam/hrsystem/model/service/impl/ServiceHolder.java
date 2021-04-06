package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.UserReportService;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.VacancyService;

/**
 * Enumeration with a single object in it (thread-safe singleton) used to different service implementations objects manage.
 *
 * @author Aleksey Klevitov
 */
public enum ServiceHolder {
    /**
     * Represents a singleton pattern realization.
     */
    HOLDER;
    private final ApplicantRequestService applicantRequestService = new ApplicantRequestServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final VacancyService vacancyService = new VacancyServiceImpl();
    private final UserReportService userReportService = new UserReportServiceImpl();

    /**
     * Getter method that returns an applicant request service object.
     *
     * @return ApplicantRequestService object.
     */
    public ApplicantRequestService getApplicantRequestService() {
        return applicantRequestService;
    }

    /**
     * Getter method that returns a user service object.
     *
     * @return UserService object.
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Getter method that returns a vacancy service object.
     *
     * @return VacancyService object.
     */
    public VacancyService getVacancyService() {
        return vacancyService;
    }

    /**
     * Getter method that returns a user report service object.
     *
     * @return UserReportService object.
     */
    public UserReportService getUserReportService() {
        return userReportService;
    }
}
