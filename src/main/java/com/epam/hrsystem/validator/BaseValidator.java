package com.epam.hrsystem.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("([1-9][0-9]{3})-([0][1-9]|[1][0-2])-([0][1-9]|[12][0-9]|[3][01])");

    private BaseValidator() {
    }

    public static boolean isDateFormatValid(String date) {
        if (date == null) {
            return false;
        }
        Matcher matcher = DATE_FORMAT_PATTERN.matcher(date);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Date isn't valid: " + date);
        }
        return result;
    }
}
