package com.epam.hrsystem.model.entity;

import java.time.LocalDate;

/**
 * Entity class represents an applicant request.
 *
 * @author Aleksey Klevitov
 */
public class ApplicantRequest {
    private long id;
    private String summary;
    private ApplicantState applicantState;
    private User applicant;
    private Vacancy vacancy;
    private InterviewResult basicInterviewResult;
    private InterviewResult technicalInterviewResult;
    private LocalDate technicalInterviewDate;

    /**
     * Constructs an ApplicantRequest object.
     */
    public ApplicantRequest() {
    }

    /**
     * Constructs an ApplicantRequest object with given id, summary, applicantState, applicant, vacancy.
     *
     * @param id             long value of applicant request's id.
     * @param summary        String object of applicant request's summary.
     * @param applicantState ApplicantState object of applicant request's applicant state.
     * @param applicant      User object of applicant request's applicant.
     * @param vacancy        Vacancy object of applicant request's vacancy.
     */
    public ApplicantRequest(long id, String summary, ApplicantState applicantState, User applicant,
                            Vacancy vacancy) {
        this.id = id;
        this.summary = summary;
        this.applicantState = applicantState;
        this.applicant = applicant;
        this.vacancy = vacancy;
    }

    /**
     * Constructs an ApplicantRequest object with given summary, applicantState.
     *
     * @param summary        String object of applicant request's summary.
     * @param applicantState ApplicantState object of applicant request's applicant state.
     */
    public ApplicantRequest(String summary, ApplicantState applicantState) {
        this.summary = summary;
        this.applicantState = applicantState;
    }

    /**
     * Getter method of applicant request's id.
     *
     * @return long value of applicant request's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter method of applicant request's summary.
     *
     * @return String object of applicant request's summary.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Setter method of applicant request's summary.
     *
     * @param summary String object of applicant request's summary.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Getter method of applicant request's applicant state.
     *
     * @return ApplicantState object of applicant request's applicant state.
     */
    public ApplicantState getApplicantState() {
        return applicantState;
    }

    /**
     * Setter method of applicant request's applicant state.
     *
     * @param applicantState ApplicantState object of applicant request's applicant state.
     */
    public void setApplicantState(ApplicantState applicantState) {
        this.applicantState = applicantState;
    }

    /**
     * Getter method of applicant request's applicant.
     *
     * @return User object of applicant request's applicant.
     */
    public User getApplicant() {
        return applicant;
    }

    /**
     * Setter method of applicant request's applicant.
     *
     * @param applicant User object of applicant request's applicant.
     */
    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    /**
     * Getter method of applicant request's vacancy.
     *
     * @return Vacancy object of applicant request's vacancy.
     */
    public Vacancy getVacancy() {
        return vacancy;
    }

    /**
     * Setter method of applicant request's vacancy.
     *
     * @param vacancy Vacancy object of applicant request's vacancy.
     */
    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    /**
     * Getter method of applicant request's basic interview result.
     *
     * @return InterviewResult object of applicant request's basic interview result.
     */
    public InterviewResult getBasicInterviewResult() {
        return basicInterviewResult;
    }

    /**
     * Setter method of applicant request's basic interview result.
     *
     * @param basicInterviewResult InterviewResult object of applicant request's basic interview result.
     */
    public void setBasicInterviewResult(InterviewResult basicInterviewResult) {
        this.basicInterviewResult = basicInterviewResult;
    }

    /**
     * Getter method of applicant request's technical interview result.
     *
     * @return InterviewResult object of applicant request's technical interview result.
     */
    public InterviewResult getTechnicalInterviewResult() {
        return technicalInterviewResult;
    }

    /**
     * Setter method of applicant request's technical interview result.
     *
     * @param technicalInterviewResult InterviewResult object of applicant request's technical interview result.
     */
    public void setTechnicalInterviewResult(InterviewResult technicalInterviewResult) {
        this.technicalInterviewResult = technicalInterviewResult;
    }

    /**
     * Getter method of applicant request's technical interview date.
     *
     * @return LocalDate object of applicant request's technical interview date.
     */
    public LocalDate getTechnicalInterviewDate() {
        return technicalInterviewDate;
    }

    /**
     * Setter method of applicant request's technical interview date.
     *
     * @param technicalInterviewDate LocalDate object of applicant request's technical interview date.
     */
    public void setTechnicalInterviewDate(LocalDate technicalInterviewDate) {
        this.technicalInterviewDate = technicalInterviewDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ApplicantRequest other = (ApplicantRequest) obj;
        return (this.id == other.id && this.applicantState == other.applicantState) &&
                (this.applicant != null ? this.applicant.equals(other.applicant) : other.applicant == null) &&
                (this.summary != null ? this.summary.equals(other.summary) : other.summary == null) &&
                (this.vacancy != null ? this.vacancy.equals(other.vacancy) : other.vacancy == null) &&
                (this.basicInterviewResult != null ? this.basicInterviewResult.equals(other.basicInterviewResult) : other.basicInterviewResult == null) &&
                (this.technicalInterviewResult != null ? this.technicalInterviewResult.equals(other.technicalInterviewResult) : other.technicalInterviewResult == null) &&
                (this.technicalInterviewDate != null ? this.technicalInterviewDate.compareTo(other.technicalInterviewDate) == 0 : other.technicalInterviewDate == null);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (applicantState != null ? applicantState.hashCode() : 0);
        result = 31 * result + (applicant != null ? applicant.hashCode() : 0);
        result = 31 * result + (vacancy != null ? vacancy.hashCode() : 0);
        result = 31 * result + (basicInterviewResult != null ? basicInterviewResult.hashCode() : 0);
        result = 31 * result + (technicalInterviewResult != null ? technicalInterviewResult.hashCode() : 0);
        result = 31 * result + (technicalInterviewDate != null ? technicalInterviewDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ("id = " + id +
                "\nsummary = " + summary +
                "\napplicant state = " + applicantState +
                "\napplicant id = " + applicant.getId() +
                "\nvacancy id = " + vacancy.getId() +
                "\nbasic interview result = " + basicInterviewResult +
                "\ntechnical interview result = " + technicalInterviewResult +
                "\ntechnical interview date = " + technicalInterviewDate);
    }
}
