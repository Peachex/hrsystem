package com.epam.hrsystem.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseValidator {
    private static final Pattern ID_PATTERN = Pattern.compile("\\d{1,19}");

    public static boolean isIdValid(String id) {
        if (id == null) {
            return false;
        }
        Matcher matcher = ID_PATTERN.matcher(id);
        return matcher.matches();
    }
}
