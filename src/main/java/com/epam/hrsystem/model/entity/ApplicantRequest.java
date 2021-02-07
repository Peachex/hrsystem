package com.epam.hrsystem.model.entity;

public class ApplicantRequest {
    private long id;
    private String summaryFileName;
    private ApplicantState applicantState;
    private User applicant;
    private Vacancy vacancy;
    private InterviewResult interviewResult;

    public ApplicantRequest(long id, String summaryFileName, ApplicantState applicantState, User applicant,
                            Vacancy vacancy, InterviewResult interviewResult) {
        this.id = id;
        this.summaryFileName = summaryFileName;
        this.applicantState = applicantState;
        this.applicant = applicant;
        this.vacancy = vacancy;
        this.interviewResult = interviewResult;
    }

    public long getId() {
        return id;
    }

    public String getSummaryFileName() {
        return summaryFileName;
    }

    public void setSummaryFileName(String summaryFileName) {
        this.summaryFileName = summaryFileName;
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

    public InterviewResult getInterviewResult() {
        return interviewResult;
    }

    public void setInterviewResult(InterviewResult interviewResult) {
        this.interviewResult = interviewResult;
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
                (this.summaryFileName != null ? this.summaryFileName.equals(other.summaryFileName) : other.summaryFileName == null) &&
                (this.vacancy != null ? this.vacancy.equals(other.vacancy) : other.vacancy == null) &&
                (this.interviewResult != null ? this.interviewResult.equals(other.interviewResult) : other.interviewResult == null);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (summaryFileName != null ? summaryFileName.hashCode() : 0);
        result = 31 * result + (applicantState != null ? applicantState.hashCode() : 0);
        result = 31 * result + (applicant != null ? applicant.hashCode() : 0);
        result = 31 * result + (vacancy != null ? vacancy.hashCode() : 0);
        result = 31 * result + (interviewResult != null ? interviewResult.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(id).append("\n");
        sb.append("summary file name = ").append(summaryFileName).append("\n");
        sb.append("applicant state = ").append(applicantState).append("\n");
        sb.append("applicant id = ").append(applicant.getId()).append("\n");
        sb.append("vacancy id = ").append(vacancy.getId()).append("\n");
        sb.append("interview result id = ").append(interviewResult.getId()).append("\n");
        return sb.toString();
    }
}
