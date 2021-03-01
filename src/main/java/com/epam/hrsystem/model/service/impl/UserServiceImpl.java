package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.controller.attribute.Constant;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.creator.Creator;
import com.epam.hrsystem.model.creator.impl.UserCreator;
import com.epam.hrsystem.model.dao.impl.SqlQuery;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.dao.impl.UserDaoImpl;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserRole;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.util.Encryptor;
import com.epam.hrsystem.validator.BaseValidator;
import com.epam.hrsystem.validator.UserValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public enum UserServiceImpl implements UserService {
    INSTANCE;
    private static final UserDao dao = UserDaoImpl.INSTANCE;

    @Override
    public Optional<User> login(String email, String password) throws ServiceException {
        Optional<User> result = Optional.empty();
        try {
            if (UserValidator.isEmailValid(email) && UserValidator.isPasswordValid(password)) {
                if (!dao.isEmailAvailable(email)) {
                    String passwordFromDatabase = dao.findPasswordByEmail(email).get();
                    Optional<Byte> activityValue = dao.findUserActivity(email);
                    if (Encryptor.check(password, passwordFromDatabase) && activityValue.get() == 1) {
                        result = dao.login(email);
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean register(Map<String, String> fields) throws ServiceException {
        boolean result = false;
        Creator<User> creator = UserCreator.INSTANCE;
        Optional<User> userOptional = creator.create(fields);
        try {
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (dao.isEmailAvailable(user.getEmail())) {
                    String password = fields.get(RequestParameter.PASSWORD);
                    if (UserValidator.isPasswordValid(password)) {
                        String passwordRepeat = fields.get(RequestParameter.REPEATED_PASSWORD);
                        if (UserValidator.isRepeatPasswordValid(password, passwordRepeat)) {
                            String encryptedPassword = Encryptor.encrypt(password);
                            result = dao.add(userOptional.get(), encryptedPassword);
                        }
                    }
                } else {
                    fields.put(RequestParameter.EMAIL, Constant.EMAIL_AVAILABLE_ERROR_MESSAGE);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean blockUser(long userId) throws ServiceException {
        boolean result;
        try {
            result = dao.updateUserActivity(userId, (byte) 0);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean unblockUser(long userId) throws ServiceException {
        boolean result;
        try {
            result = dao.updateUserActivity(userId, (byte) 1);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean changePassword(long userId, String newPassword) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isPasswordValid(newPassword)) {
                String encryptedPassword = Encryptor.encrypt(newPassword);
                result = dao.updatePassword(userId, encryptedPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean changePhoto(long userId, String photoName) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isPhotoNameValid(photoName)) {
                result = dao.changePhoto(userId, photoName);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            List<User> users = dao.findUsersBySqlQuery(SqlQuery.SQL_SELECT_ALL_USERS);
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findBlockedUsers() throws ServiceException {
        try {
            List<User> users = dao.findUsersBySqlQuery(SqlQuery.SQL_SELECT_BLOCKED_USERS);
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findNotBlockedUsers() throws ServiceException {
        try {
            List<User> users = dao.findUsersBySqlQuery(SqlQuery.SQL_SELECT_NOT_BLOCKED_USERS);
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeUserRole(long userId, String role) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isUserRoleValid(role)) {
                UserRole userRole = UserRole.valueOf(role.toUpperCase(Locale.ROOT));
                result = dao.changeUserRole(userId, userRole);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Optional<User> updateProfile(long userId, Map<String, String> fields) throws ServiceException {
        try {
            Optional<User> userOptional = dao.findUserById(userId);
            if (userOptional.isPresent() && UserValidator.isEditFormValid(fields)) {
                User user = userOptional.get();
                if (user.getEmail().equals(fields.get(RequestParameter.EMAIL)) || dao.isEmailAvailable(fields.get(RequestParameter.EMAIL))) {
                    updateUserFields(user, fields);
                    if (dao.updateProfile(user))
                        return Optional.of(user);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.empty();
    }

    private void updateUserFields(User user, Map<String, String> fields) {
        String newFirstName = fields.get(RequestParameter.FIRST_NAME);
        if (UserValidator.isNameValid(newFirstName)) {
            user.setFirstName(newFirstName);
        }
        String newLastName = fields.get(RequestParameter.LAST_NAME);
        if (UserValidator.isNameValid(newLastName)) {
            user.setLastName(newLastName);
        }
        if (BaseValidator.isDateFormatValid(fields.get(RequestParameter.DATE_OF_BIRTH))) {
            LocalDate newDateOfBirth = LocalDate.parse(fields.get(RequestParameter.DATE_OF_BIRTH));
            user.setDateOfBirth(newDateOfBirth);
        }
        String newPhoneNumber = fields.get(RequestParameter.PHONE_NUMBER);
        if (UserValidator.isPhoneNumberValid(newPhoneNumber)) {
            user.setPhoneNumber(newPhoneNumber);
        }
        String newEmail = fields.get(RequestParameter.EMAIL);
        if (UserValidator.isEmailValid(newEmail)) {
            user.setEmail(newEmail);
        }
    }
}
