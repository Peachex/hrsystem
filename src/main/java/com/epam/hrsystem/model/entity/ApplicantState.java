package com.epam.hrsystem.model.entity;

/**
 * Enumeration of applicants' state.
 */
public enum ApplicantState {
    /**
     * Represents the state when applicant left a request.
     */
    LEFT_REQUEST,
    /**
     * Represents the state when applicant is ready for a technical interview.
     */
    READY_FOR_TECHNICAL_INTERVIEW,
    /**
     * Represents the state when applicant has passed all interviews.
     */
    PASSED,
    /**
     * Represents the state when applicant has failed an interview.
     */
    FAILED
}
