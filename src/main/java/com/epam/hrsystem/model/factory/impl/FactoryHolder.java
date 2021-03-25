package com.epam.hrsystem.model.factory.impl;

import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.InterviewResult;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserReport;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.factory.EntityFactory;

public enum FactoryHolder {
    HOLDER;
    private final EntityFactory<ApplicantRequest> applicantRequestFactory = new ApplicantRequestFactory();
    private final EntityFactory<InterviewResult> interviewResultFactory = new InterviewResultFactory();
    private final EntityFactory<User> userFactory = new UserFactory();
    private final EntityFactory<Vacancy> vacancyFactory = new VacancyFactory();
    private final EntityFactory<UserReport> userReportFactory = new UserReportFactory(); //fixme change type to interface type

    public EntityFactory<ApplicantRequest> getApplicantRequestFactory() {
        return applicantRequestFactory;
    }

    public EntityFactory<InterviewResult> getInterviewResultFactory() {
        return interviewResultFactory;
    }

    public EntityFactory<User> getUserFactory() {
        return userFactory;
    }

    public EntityFactory<Vacancy> getVacancyFactory() {
        return vacancyFactory;
    }

    public EntityFactory<UserReport> getUserReportFactory() {
        return userReportFactory;
    }
}
