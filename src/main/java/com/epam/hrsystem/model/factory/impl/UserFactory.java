package com.epam.hrsystem.model.factory.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserRole;

import static com.epam.hrsystem.validator.UserValidator.isRegisterFormValid;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * EntityFactory implementation used to create a User object.
 *
 * @author Aleksey Klevitov
 */
public class UserFactory implements EntityFactory<User> {
    private static final UserRole DEFAULT_ROLE = UserRole.APPLICANT;
    private static final String DEFAULT_PHOTO_NAME = "default_avatar.png";
    private static final boolean DEFAULT_ACTIVE_VALUE = true;
    private static final Lock locker = new ReentrantLock();
    private static EntityFactory<User> instance;

    /**
     * Constructs a UserFactory object.
     */
    private UserFactory() {
    }

    /**
     * Returns an EntityFactory object.
     */
    public static EntityFactory<User> getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new UserFactory();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public Optional<User> create(Map<String, String> fields) {
        Optional<User> result = Optional.empty();
        if (isRegisterFormValid(fields)) {
            String email = fields.get(RequestParameter.EMAIL);
            String firstName = fields.get(RequestParameter.FIRST_NAME);
            String lastName = fields.get(RequestParameter.LAST_NAME);
            String dateOfBirth = fields.get(RequestParameter.DATE_OF_BIRTH);
            String phoneNumber = fields.get(RequestParameter.PHONE_NUMBER);
            result = Optional.of(new User(DEFAULT_ROLE, DEFAULT_ACTIVE_VALUE, DEFAULT_PHOTO_NAME, firstName, lastName,
                    LocalDate.parse(dateOfBirth), phoneNumber, email));
        }
        return result;
    }
}
