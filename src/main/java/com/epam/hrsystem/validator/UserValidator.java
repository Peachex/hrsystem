package com.epam.hrsystem.validator;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{3,35}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("((\\w)([-.](\\w))?)+@((\\w)([-.](\\w))?)+.[a-zA-Z]{2,4}");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("(\\+?(((\\d+-\\d+)+)|(\\d{2,20})|((\\d+\\s\\d+)+)))|" +
            "(\\(\\+?\\d+\\)[-\\s]?(((\\d+-\\d+)+)|(\\d+)|((\\d+\\s\\d+)+)))");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[\\w\\s\\p{Punct}]{6,80}");
    private static final int EMAIL_MAX_LENGTH = 50;
    private static final int PHONE_NUMBER_MAX_LENGTH = 20;
    private static final int PHOTO_NAME_MAX_LENGTH = 50;

    private UserValidator() {
    }

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
        if (!BaseValidator.isDateFormatValid(dateOfBirth)) {
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
        if (!BaseValidator.isDateFormatValid(dateOfBirth)) {
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

    public static boolean isUserRoleValid(String role) {
        boolean result = Arrays.stream(UserRole.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(role.toUpperCase());
        if (!result) {
            logger.log(Level.DEBUG, "Role isn't valid: " + role);
        }
        return result;
    }

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

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        boolean result = matcher.matches() && email.length() <= EMAIL_MAX_LENGTH;
        if (!result) {
            logger.log(Level.DEBUG, "Email isn't valid: " + email);
        }
        return result;
    }

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
}
