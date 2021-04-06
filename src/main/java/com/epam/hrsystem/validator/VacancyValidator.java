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
 * Vacancy validator class used to check vacancies' fields values.
 *
 * @author Aleksey Klevitov
 */
public class VacancyValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern POSITION_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{3,1000}");
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{3,10000}");
    private static final Pattern COUNTRY_PATTERN = Pattern.compile("[a-zA-Zа-яА-я\\s]{3,50}");
    private static final Pattern CITY_PATTERN = Pattern.compile("[a-zA-Zа-яА-я\\s]{2,50}");

    private VacancyValidator() {
    }

    /**
     * Checks if vacancy form is valid.
     *
     * @param fields Map object with vacancy's fields with RequestParameter's constants as keys inside.
     * @return boolean value. True if the vacancy form is valid, false otherwise.
     */
    public static boolean isVacancyFormValid(Map<String, String> fields) {
        boolean result = true;
        String position = fields.get(RequestParameter.POSITION);
        if (!isPositionValid(position)) {
            fields.put(RequestParameter.POSITION, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String description = fields.get(RequestParameter.DESCRIPTION);
        if (!isDescriptionValid(description)) {
            fields.put(RequestParameter.DESCRIPTION, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String country = fields.get(RequestParameter.COUNTRY);
        if (!isCountryValid(country)) {
            fields.put(RequestParameter.COUNTRY, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String city = fields.get(RequestParameter.CITY);
        if (!isCityValid(city)) {
            fields.put(RequestParameter.CITY, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        return result;
    }

    /**
     * Checks if vacancy's position is valid.
     *
     * @param position String object of vacancy's position.
     * @return boolean value. True if the vacancy's position is valid, false otherwise.
     */
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

    /**
     * Checks if vacancy's description is valid.
     *
     * @param description String object of vacancy's description.
     * @return boolean value. True if the vacancy's description is valid, false otherwise.
     */
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

    /**
     * Checks if vacancy's country is valid.
     *
     * @param country String object of vacancy's country.
     * @return boolean value. True if the vacancy's country is valid, false otherwise.
     */
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

    /**
     * Checks if vacancy's city is valid.
     *
     * @param city String object of vacancy's city.
     * @return boolean value. True if the vacancy's city is valid, false otherwise.
     */
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
