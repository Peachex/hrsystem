package com.epam.hrsystem.validator;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterviewResultValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern RATING_PATTERN = Pattern.compile("[1-9]|10");
    private static final Pattern COMMENT_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{1,25000}");

    private InterviewResultValidator() {
    }

    public static boolean isInterviewResultFormValid(Map<String, String> fields) {
        boolean result = true;
        String rating = fields.get(RequestParameter.INTERVIEW_RESULT_RATING);
        if (!isRatingValid(rating)) {
            fields.put(RequestParameter.INTERVIEW_RESULT_RATING, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String comment = fields.get(RequestParameter.INTERVIEW_RESULT_COMMENT);
        if (!isCommentValid(comment)) {
            fields.put(RequestParameter.INTERVIEW_RESULT_COMMENT, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        return result;
    }

    public static boolean isRatingValid(String rating) {
        if (rating == null) {
            return false;
        }
        Matcher matcher = RATING_PATTERN.matcher(rating);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Rating isn't valid: " + rating);
        }
        return matcher.matches();
    }

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
}
