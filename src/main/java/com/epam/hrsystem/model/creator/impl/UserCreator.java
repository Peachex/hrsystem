package com.epam.hrsystem.model.creator.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.creator.Creator;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserRole;

import static com.epam.hrsystem.validator.UserValidator.isRegisterFormValid;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class UserCreator implements Creator<User> {
    private static final UserRole DEFAULT_ROLE = UserRole.APPLICANT;
    private static final String DEFAULT_PHOTO_NAME = "avatar_photo_name";
    private static final boolean DEFAULT_ACTIVE_VALUE = true;

    @Override
    public Optional<User> create(Map<String, String> fields) {
        Optional<User> result = Optional.empty();
        if (isRegisterFormValid(fields)) {
            String email = fields.get(RequestParameter.EMAIL);
            String firstName = fields.get(RequestParameter.FIRST_NAME);
            String lastName = fields.get(RequestParameter.LAST_NAME);
            String dateOfBirth = fields.get(RequestParameter.DATE_OF_BIRTH);
            String phoneNumber = fields.get(RequestParameter.PHONE_NUMBER);
            User user = new User(DEFAULT_ROLE, DEFAULT_ACTIVE_VALUE, DEFAULT_PHOTO_NAME, firstName, lastName, LocalDate.parse(dateOfBirth), phoneNumber, email);
            result = Optional.of(user);
        }
        return result;
    }
}
