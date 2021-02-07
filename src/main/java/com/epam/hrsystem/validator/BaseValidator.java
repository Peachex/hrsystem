package com.epam.hrsystem.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseValidator {
    private static final Pattern ID_PATTERN = Pattern.compile("\\d{1,19}");
    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("([1-9][0-9]{3})-([0][1-9]|[1][0-2])-([0][1-9]|[12][0-9]|[3][01])");

    public static boolean isIdValid(String id) {
        if (id == null) {
            return false;
        }
        Matcher matcher = ID_PATTERN.matcher(id);
        return matcher.matches();
    }

    public static boolean isDateFormatValid(String date) {
        if (date == null) {
            return false;
        }
        Matcher matcher = DATE_FORMAT_PATTERN.matcher(date);
        return matcher.matches();
    }
}
