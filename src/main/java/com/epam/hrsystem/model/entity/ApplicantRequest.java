package com.epam.hrsystem.model.entity;

import java.time.LocalDate;

public class ApplicantRequest {
    private long id;
    private String summary;
    private ApplicantState applicantState;
    private User applicant;
    private Vacancy vacancy;
    private InterviewResult basicInterviewResult;
    private InterviewResult technicalInterviewResult;
    private LocalDate technicalInterviewDate;

    public ApplicantRequest(long id, String summary, ApplicantState applicantState, User applicant,
                            Vacancy vacancy, InterviewResult basicInterviewResult, InterviewResult technicalInterviewResult) {
        this.id = id;
        this.summary = summary;
        this.applicantState = applicantState;
        this.applicant = applicant;
        this.vacancy = vacancy;
        this.basicInterviewResult = basicInterviewResult;
        this.technicalInterviewResult = technicalInterviewResult;
    }

    public ApplicantRequest(String summary, ApplicantState applicantState) {
        this.summary = summary;
        this.applicantState = applicantState;
    }

    public long getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ApplicantState getApplicantState() {
        return applicantState;
    }

    public void setApplicantState(ApplicantState applicantState) {
        this.applicantState = applicantState;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public InterviewResult getBasicInterviewResult() {
        return basicInterviewResult;
    }

    public void setBasicInterviewResult(InterviewResult basicInterviewResult) {
        this.basicInterviewResult = basicInterviewResult;
    }

    public InterviewResult getTechnicalInterviewResult() {
        return technicalInterviewResult;
    }

    public void setTechnicalInterviewResult(InterviewResult technicalInterviewResult) {
        this.technicalInterviewResult = technicalInterviewResult;
    }

    public LocalDate getTechnicalInterviewDate() {
        return technicalInterviewDate;
    }

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
        final StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(id).append("\n");
        sb.append("summary = ").append(summary).append("\n");
        sb.append("applicant state = ").append(applicantState).append("\n");
        sb.append("applicant id = ").append(applicant.getId()).append("\n");
        sb.append("vacancy id = ").append(vacancy.getId()).append("\n");
        sb.append("basic interview result id = ").append(basicInterviewResult.getId()).append("\n");
        sb.append("technical interview result id = ").append(technicalInterviewResult.getId()).append("\n");
        sb.append("technical interview date = ").append(technicalInterviewDate).append("\n");
        return sb.toString();
    }
}
