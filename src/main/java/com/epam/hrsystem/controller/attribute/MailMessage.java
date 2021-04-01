package com.epam.hrsystem.controller.attribute;

public class MailMessage {
    public static final String HR_SYSTEM_MAIL_SUBJECT = "HR-SYSTEM";

    public static final String CREATION_APPLICANT_REQUEST_MAIL_TEXT = "Hello! Thanks for the application you left. Wait for" +
            " telephone interview, our recruiter will contact you. Good luck!";

    public static final String APPLICANT_IS_READY_FOR_TECHNICAL_INTERVIEW_MAIL_TEXT = "Hello! Congratulations, you have successfully passed the basic interview." +
            " Our recruiter will contact you and tell about next steps.";

    public static final String APPLICANT_PASSED_INTERVIEWS_MAIL_TEXT = "Hello! Congratulations, you have successfully passed all interviews." +
            " Our recruiter will contact you and tell about next steps.";

    public static final String APPLICANT_FAILED_INTERVIEWS_MAIL_TEXT = "Hello! Sorry but you haven't successfully passed all interviews." +
            " It's okay because you've got valuable experience that will help you in the future.";

    public static final String SCHEDULE_TECHNICAL_INTERVIEW_PART_1_MAIL_TEXT = "Hello! As you have successfully passed the basic interview" +
            " we have scheduled a technical interview for you on date: ";

    public static final String SCHEDULE_TECHNICAL_INTERVIEW_PART_2_MAIL_TEXT = " Be ready for technical interview. Our recruiter will remind you.";

    public static final String CREATION_USER_REPORT_MAIL_TEXT = "Hello! Thanks for the report you left. We will review your report and after processing you" +
            " will receive a response to your e-mail.";

    public static final String CREATION_USER_REPORT_RESPONSE_MAIL_TEXT = "Hello! The report you left has already been processed. Response: ";

    private MailMessage() {
    }
}
