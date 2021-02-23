package com.epam.hrsystem.validator;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacancyValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern POSITION_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{3,1000}");
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{3,10000}");
    private static final Pattern COUNTRY_PATTERN = Pattern.compile("[a-zA-Zа-яА-я\\s]{3,50}");
    private static final Pattern CITY_PATTERN = Pattern.compile("[a-zA-Zа-яА-я\\s]{2,50}");

    private VacancyValidator() {
    }

    public static boolean isVacancyFormValid(Map<String, String> fields) {
        boolean result = true;
        String position = fields.get(RequestParameter.POSITION);
        if (!isPositionValid(position)) {
            fields.put(RequestParameter.POSITION, "Invalid input!");
            result = false;
        }
        String description = fields.get(RequestParameter.DESCRIPTION);
        if (!isDescriptionValid(description)) {
            fields.put(RequestParameter.DESCRIPTION, "Invalid input!");
            result = false;
        }
        String country = fields.get(RequestParameter.COUNTRY);
        if (!isCountryValid(country)) {
            fields.put(RequestParameter.COUNTRY, "Invalid input!");
            result = false;
        }
        String city = fields.get(RequestParameter.CITY);
        if (!isCityValid(city)) {
            fields.put(RequestParameter.CITY, "Invalid input!");
            result = false;
        }
        return result;
    }

    public static boolean isPositionValid(String position) {
        if (position == null) {
            return false;
        }
        Matcher matcher = POSITION_PATTERN.matcher(position);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Position isn't valid: " + position);
        }
        return result;
    }

    public static boolean isDescriptionValid(String description) {
        if (description == null) {
            return false;
        }
        Matcher matcher = DESCRIPTION_PATTERN.matcher(description);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Description isn't valid: " + description);
        }
        return result;
    }

    public static boolean isCountryValid(String country) {
        if (country == null) {
            return false;
        }
        Matcher matcher = COUNTRY_PATTERN.matcher(country);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Country isn't valid: " + country);
        }
        return result;
    }

    public static boolean isCityValid(String city) {
        if (city == null) {
            return false;
        }
        Matcher matcher = CITY_PATTERN.matcher(city);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "City isn't valid: " + city);
        }
        return result;
    }
}
