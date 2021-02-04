package com.epam.hrsystem.validator;

import com.epam.hrsystem.model.entity.UserRole;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{3,35}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("((\\w)([-.](\\w))?)+@((\\w)([-.](\\w))?)+.[a-zA-Zа-яА-Я]{2,4}");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("(\\+?(((\\d+-\\d+)+)|(\\d{2,20})|((\\d+\\s\\d+)+)))|" +
            "(\\(\\+?\\d+\\)[-\\s]?(((\\d+-\\d+)+)|(\\d+)|((\\d+\\s\\d+)+)))");
    private static final int EMAIL_MAX_LENGTH = 50;
    private static final int PHONE_NUMBER_MAX_LENGTH = 20;

    public static boolean isUserRoleValid(String role) {
        boolean result = Arrays.stream(UserRole.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(role.toUpperCase());
        return result;
    }

    public static boolean isNameValid(String name) {
        if (name == null) {
            return false;
        }
        Matcher matcher = NAME_PATTERN.matcher(name);
        return matcher.matches();
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phoneNumber);
        return (matcher.matches() && phoneNumber.length() <= PHONE_NUMBER_MAX_LENGTH);
    }

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return (matcher.matches() && email.length() <= EMAIL_MAX_LENGTH);
    }
}
