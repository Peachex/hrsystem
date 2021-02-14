package com.epam.hrsystem.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacancyValidator {
    private static final int POSITION_MAX_LENGTH = 5000;
    private static final int DESCRIPTION_MAX_LENGTH = 15000;
    private static final Pattern COUNTRY_PATTERN = Pattern.compile("[a-zA-Zа-яА-я\\s]{3,50}");
    private static final Pattern CITY_PATTERN = Pattern.compile("[a-zA-Zа-яА-я\\s]{2,50}");

    public static boolean isPositionValid(String position) {
        if (position == null || position.isEmpty()) {
            return false;
        }
        return (position.length() < POSITION_MAX_LENGTH);
    }

    public static boolean isDescriptionValid(String description) {
        if (description == null || description.isEmpty()) {
            return false;
        }
        return (description.length() < DESCRIPTION_MAX_LENGTH);
    }

    public static boolean isCountryValid(String country) {
        if (country == null) {
            return false;
        }
        Matcher matcher = COUNTRY_PATTERN.matcher(country);
        return matcher.matches();
    }

    public static boolean isCityValid(String city) {
        if (city == null) {
            return false;
        }
        Matcher matcher = CITY_PATTERN.matcher(city);
        return matcher.matches();
    }
}
