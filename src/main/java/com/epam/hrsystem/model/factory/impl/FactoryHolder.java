package com.epam.hrsystem.model.factory.impl;

import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.InterviewResult;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserReport;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.factory.EntityFactory;

/**
 * Enumeration with a single object in it (thread-safe singleton) used to EntityFactory implementations objects manage.
 *
 * @author Aleksey Klevitov
 */
public enum FactoryHolder {
    /**
     * Represents a singleton pattern realization.
     */
    HOLDER;
    private final EntityFactory<ApplicantRequest> applicantRequestFactory = new ApplicantRequestFactory();
    private final EntityFactory<InterviewResult> interviewResultFactory = new InterviewResultFactory();
    private final EntityFactory<User> userFactory = new UserFactory();
    private final EntityFactory<Vacancy> vacancyFactory = new VacancyFactory();
    private final EntityFactory<UserReport> userReportFactory = new UserReportFactory();

    /**
     * Getter method that returns an applicant request factory object.
     *
     * @return ApplicantRequestFactory object.
     */
    public EntityFactory<ApplicantRequest> getApplicantRequestFactory() {
        return applicantRequestFactory;
    }

    /**
     * Getter method that returns interview result factory object.
     *
     * @return InterviewResultFactory object.
     */
    public EntityFactory<InterviewResult> getInterviewResultFactory() {
        return interviewResultFactory;
    }

    /**
     * Getter method that returns user factory object.
     *
     * @return UserFactory object.
     */
    public EntityFactory<User> getUserFactory() {
        return userFactory;
    }

    /**
     * Getter method that returns vacancy factory object.
     *
     * @return VacancyFactory object.
     */
    public EntityFactory<Vacancy> getVacancyFactory() {
        return vacancyFactory;
    }

    /**
     * Getter method that returns user report factory object.
     *
     * @return UserReportFactory object.
     */
    public EntityFactory<UserReport> getUserReportFactory() {
        return userReportFactory;
    }
}
