package com.epam.hrsystem.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterviewResultValidator {
    private static final Pattern RATING_PATTERN = Pattern.compile("[1-9]|10");
    private static final int COMMENT_MAX_LENGTH = 25000;

    public static boolean isRatingValid(String rating) {
        if (rating == null) {
            return false;
        }
        Matcher matcher = RATING_PATTERN.matcher(rating);
        return matcher.matches();
    }

    public static boolean isCommentValid(String comment) {
        if (comment == null) {
            return false;
        }
        return (comment.length() > 0 && comment.length() < COMMENT_MAX_LENGTH);
    }
}
