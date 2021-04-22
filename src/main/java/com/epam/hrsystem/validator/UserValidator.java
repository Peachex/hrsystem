package com.epam.hrsystem.validator;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * User validator class used to check users' fields values.
 *
 * @author Aleksey Klevitov
 */
public final class UserValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{3,255}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("((\\w)([-.](\\w))?){1,64}@((\\w)([-.](\\w))?){1,251}.[a-zA-Z]{2,4}");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("(\\+?(((\\d+-\\d+)+)|(\\d{2,20})|((\\d+\\s\\d+)+)))|" +
            "(\\(\\+?\\d+\\)[-\\s]?(((\\d+-\\d+)+)|(\\d+)|((\\d+\\s\\d+)+)))");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[а-яА-Я\\w\\s\\p{Punct}]{6,255}");
    private static final int PHONE_NUMBER_MAX_LENGTH = 20;
    private static final int PHOTO_NAME_MAX_LENGTH = 50;
    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("([1-2][0-9]{3})-([0][1-9]|[1][0-2])-([0][1-9]|[12][0-9]|[3][01])");

    private UserValidator() {
    }

    /**
     * Checks if register form is valid.
     *
     * @param fields Map object with user's fields with RequestParameter's constants as keys inside.
     * @return boolean value. True if the register form is valid, false otherwise.
     */
    public static boolean isRegisterFormValid(Map<String, String> fields) {
        boolean result = true;
        String firstName = fields.get(RequestParameter.FIRST_NAME);
        if (!isNameValid(firstName)) {
            fields.put(RequestParameter.FIRST_NAME, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String lastName = fields.get(RequestParameter.LAST_NAME);
        if (!isNameValid(lastName)) {
            fields.put(RequestParameter.LAST_NAME, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String dateOfBirth = fields.get(RequestParameter.DATE_OF_BIRTH);
        if (!isDateFormatValid(dateOfBirth)) {
            fields.put(RequestParameter.DATE_OF_BIRTH, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String phoneNumber = fields.get(RequestParameter.PHONE_NUMBER);
        if (!isPhoneNumberValid(phoneNumber)) {
            fields.put(RequestParameter.PHONE_NUMBER, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String email = fields.get(RequestParameter.EMAIL);
        if (!isEmailValid(email)) {
            fields.put(RequestParameter.EMAIL, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String password = fields.get(RequestParameter.PASSWORD);
        if (!isPasswordValid(password)) {
            fields.put(RequestParameter.PASSWORD, "");
            result = false;
        }
        String repeatPassword = fields.get(RequestParameter.REPEATED_PASSWORD);
        if (!isRepeatPasswordValid(password, repeatPassword)) {
            fields.put(RequestParameter.REPEATED_PASSWORD, "");
            result = false;
        }
        return result;
    }

    /**
     * Checks if edit form is valid.
     *
     * @param fields Map object with user's fields with RequestParameter's constants as keys inside.
     * @return boolean value. True if the edit form is valid, false otherwise.
     */
    public static boolean isEditFormValid(Map<String, String> fields) {
        boolean result = true;
        String firstName = fields.get(RequestParameter.FIRST_NAME);
        if (!isNameValid(firstName)) {
            fields.put(RequestParameter.FIRST_NAME, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String lastName = fields.get(RequestParameter.LAST_NAME);
        if (!isNameValid(lastName)) {
            fields.put(RequestParameter.LAST_NAME, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String dateOfBirth = fields.get(RequestParameter.DATE_OF_BIRTH);
        if (!isDateFormatValid(dateOfBirth)) {
            fields.put(RequestParameter.DATE_OF_BIRTH, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String phoneNumber = fields.get(RequestParameter.PHONE_NUMBER);
        if (!isPhoneNumberValid(phoneNumber)) {
            fields.put(RequestParameter.PHONE_NUMBER, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        String email = fields.get(RequestParameter.EMAIL);
        if (!isEmailValid(email)) {
            fields.put(RequestParameter.EMAIL, JspAttribute.INVALID_INPUT_DATA_MESSAGE);
            result = false;
        }
        return result;
    }

    /**
     * Checks if change password form is valid.
     *
     * @param fields Map object with user's fields with RequestParameter's constants as keys inside.
     * @return boolean value. True if the change password form is valid, false otherwise.
     */
    public static boolean isChangePasswordFormValid(Map<String, String> fields) {
        boolean result = true;
        String newPassword = fields.get(RequestParameter.NEW_PASSWORD);
        if (!isPasswordValid(newPassword)) {
            fields.put(RequestParameter.NEW_PASSWORD, "");
            result = false;
        }
        String repeatedNewPassword = fields.get(RequestParameter.REPEATED_NEW_PASSWORD);
        if (!isRepeatPasswordValid(newPassword, repeatedNewPassword)) {
            fields.put(RequestParameter.REPEATED_NEW_PASSWORD, "");
            result = false;
        }
        return result;
    }

    /**
     * Checks if user's role is valid.
     *
     * @param role String object of user's role.
     * @return boolean value. True if the user's role is valid, false otherwise.
     */
    public static boolean isUserRoleValid(String role) {
        if (role == null || role.isEmpty()) {
            return false;
        }
        boolean result = Arrays.stream(UserRole.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(role.toUpperCase());
        if (!result) {
            logger.log(Level.DEBUG, "Role isn't valid: " + role);
        }
        return result;
    }

    /**
     * Checks if user's name is valid.
     *
     * @param name String object of user's name.
     * @return boolean value. True if the user's name is valid, false otherwise.
     */
    public static boolean isNameValid(String name) {
        if (name == null) {
            return false;
        }
        Matcher matcher = NAME_PATTERN.matcher(name);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Name isn't valid: " + name);
        }
        return result;
    }

    /**
     * Checks if user's phone number is valid.
     *
     * @param phoneNumber String object of user's phone number.
     * @return boolean value. True if the user's phone number is valid, false otherwise.
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phoneNumber);
        boolean result = matcher.matches() && phoneNumber.length() <= PHONE_NUMBER_MAX_LENGTH;
        if (!result) {
            logger.log(Level.DEBUG, "Phone number isn't valid: " + phoneNumber);
        }
        return result;
    }

    /**
     * Checks if user's email is valid.
     *
     * @param email String object of user's email.
     * @return boolean value. True if the user's email is valid, false otherwise.
     */
    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Email isn't valid: " + email);
        }
        return result;
    }

    /**
     * Checks if user's photo name is valid.
     *
     * @param photoName String object of user's photo name.
     * @return boolean value. True if the user's photo name is valid, false otherwise.
     */
    public static boolean isPhotoNameValid(String photoName) {
        if (photoName == null) {
            return false;
        }
        boolean result = photoName.length() > 0 && photoName.length() <= PHOTO_NAME_MAX_LENGTH;
        if (!result) {
            logger.log(Level.DEBUG, "Photo name isn't valid: " + photoName);
        }
        return result;
    }

    /**
     * Checks if user's repeat password is valid.
     *
     * @param password       String object of user's password.
     * @param repeatPassword String object of user's repeat password.
     * @return boolean value. True if the user's repeat password is valid, false otherwise.
     */
    public static boolean isRepeatPasswordValid(String password, String repeatPassword) {
        if (repeatPassword == null) {
            return false;
        }
        boolean result = isPasswordValid(password) && password.equals(repeatPassword);
        if (!result) {
            logger.log(Level.DEBUG, "Repeat password isn't valid: " + repeatPassword);
        }
        return result;
    }

    /**
     * Checks if user's password is valid.
     *
     * @param password String object of user's password.
     * @return boolean value. True if the user's password is valid, false otherwise.
     */
    public static boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Password isn't valid: " + password);
        }
        return result;
    }

    /**
     * Checks if date format is valid.
     *
     * @param date String object of date.
     * @return boolean value. True if the date is valid, false otherwise.
     */
    public static boolean isDateFormatValid(String date) {
        if (date == null) {
            return false;
        }
        Matcher matcher = DATE_FORMAT_PATTERN.matcher(date);
        boolean result = matcher.matches() && LocalDate.parse(date).isBefore(LocalDate.now());
        if (!result) {
            logger.log(Level.DEBUG, "Date isn't valid: " + date);
        }
        return result;
    }
}
