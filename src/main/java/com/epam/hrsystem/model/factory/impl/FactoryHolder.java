package com.epam.hrsystem.model.factory.impl;

public enum FactoryHolder {
    HOLDER;
    private final ApplicantRequestFactory applicantRequestFactory = new ApplicantRequestFactory();
    private final InterviewResultFactory interviewResultFactory = new InterviewResultFactory();
    private final UserFactory userFactory = new UserFactory();
    private final VacancyFactory vacancyFactory = new VacancyFactory();

    public ApplicantRequestFactory getApplicantRequestFactory() {
        return applicantRequestFactory;
    }

    public InterviewResultFactory getInterviewResultFactory() {
        return interviewResultFactory;
    }

    public UserFactory getUserFactory() {
        return userFactory;
    }

    public VacancyFactory getVacancyFactory() {
        return vacancyFactory;
    }
}
