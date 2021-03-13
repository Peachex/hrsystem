package com.epam.hrsystem.controller.attribute;

public class MailMessage {
    public static final String HR_SYSTEM_MAIL_SUBJECT = "HR-SYSTEM";

    public static final String CREATION_APPLICANT_REQUEST_MAIL_TEXT = "Hello! Thanks for the application you left. Wait for" +
            " telephone interview, our recruiter will contact you. Good luck!";

    public static final String APPLICANT_IS_READY_FOR_TECHNICAL_INTERVIEW_MAIL_TEXT = "Is ready fot technical interview!";

    public static final String APPLICANT_PASSED_INTERVIEWS_MAIL_TEXT = "Passed!"; //todo write mail text

    public static final String APPLICANT_FAILED_INTERVIEWS_MAIL_TEXT = "Failed!";

    public static final String SCHEDULE_TECHNICAL_INTERVIEW_MAIL_TEXT = "Be ready for technical interview. Date: ";

    private MailMessage() {
    }
}
