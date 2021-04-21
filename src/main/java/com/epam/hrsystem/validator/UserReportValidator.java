package com.epam.hrsystem.validator;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User report validator class used to check user reports' fields values.
 *
 * @author Aleksey Klevitov
 */
public final class UserReportValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern SUBJECT_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{1,100}");
    private static final Pattern COMMENT_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{1,25000}");
    private static final Pattern RESPONSE_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{1,25000}");

    private UserReportValidator() {
    }

    /**
     * Checks if user report form is valid.
     *
     * @param fields Map object with user report's fields with RequestParameter's constants as keys inside.
     * @return boolean value. True if the user report form is valid, false otherwise.
     */
    public static boolean isUserReportFormValid(Map<String, String> fields) {
        boolean result = true;
        String subject = fields.get(RequestParameter.USER_REPORT_SUBJECT);
        if (!isSubjectValid(subject)) {
            fields.put(RequestParameter.USER_REPORT_SUBJECT, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String comment = fields.get(RequestParameter.USER_REPORT_COMMENT);
        if (!isCommentValid(comment)) {
            fields.put(RequestParameter.USER_REPORT_COMMENT, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        return result;
    }

    /**
     * Checks if user report's subject is valid.
     *
     * @param subject String object of user report's subject.
     * @return boolean value. True if the user report's subject is valid, false otherwise.
     */
    public static boolean isSubjectValid(String subject) {
        if (subject == null) {
            return false;
        }
        Matcher matcher = SUBJECT_PATTERN.matcher(subject);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Subject isn't valid: " + subject);
        }
        return matcher.matches();
    }

    /**
     * Checks if user report's comment is valid.
     *
     * @param comment String object of user report's comment.
     * @return boolean value. True if the user report's comment is valid, false otherwise.
     */
    public static boolean isCommentValid(String comment) {
        if (comment == null) {
            return false;
        }
        Matcher matcher = COMMENT_PATTERN.matcher(comment);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Comment isn't valid: " + comment);
        }
        return result;
    }

    /**
     * Checks if user report's response is valid.
     *
     * @param response String object of user report's response.
     * @return boolean value. True if the user report's response is valid, false otherwise.
     */
    public static boolean isResponseValid(String response) {
        if (response == null) {
            return false;
        }
        Matcher matcher = RESPONSE_PATTERN.matcher(response);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Response isn't valid: " + response);
        }
        return result;
    }
}
