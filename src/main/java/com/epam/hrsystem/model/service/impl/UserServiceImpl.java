package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.dao.impl.UserDaoImpl;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserRole;
import com.epam.hrsystem.model.factory.impl.UserFactory;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.util.Encryptor;
import com.epam.hrsystem.validator.UserValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * UserService implementation.
 *
 * @author Aleksey Klevitov
 */
public class UserServiceImpl implements UserService {
    private static final UserDao dao = UserDaoImpl.getInstance();
    private static final String PERCENT_SIGN = "%";
    private static final EntityFactory<User> userFactory = UserFactory.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile UserService instance;

    /**
     * Constructs a UserServiceImpl object.
     */
    private UserServiceImpl() {
    }

    /**
     * Returns a UserService object.
     */
    public static UserService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new UserServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public Optional<User> login(String email, String password) throws ServiceException {
        try {
            if (UserValidator.isEmailValid(email) && UserValidator.isPasswordValid(password) && !dao.isEmailAvailable(email)) {
                Optional<String> passwordFromDatabaseOptional = dao.findPasswordByEmail(email);
                if (passwordFromDatabaseOptional.isPresent() && Encryptor.check(password, passwordFromDatabaseOptional.get())) {
                    return dao.findUserByEmail(email);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean register(Map<String, String> fields) throws ServiceException {
        try {
            Optional<User> userOptional = userFactory.create(fields);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (dao.isEmailAvailable(user.getEmail())) {
                    String password = fields.get(RequestParameter.PASSWORD);
                    String encryptedPassword = Encryptor.encrypt(password);
                    return dao.add(user, encryptedPassword);
                } else {
                    fields.put(RequestParameter.EMAIL, JspAttribute.EMAIL_AVAILABLE_ERROR_MESSAGE);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean blockUser(long userId) throws ServiceException {
        try {
            return dao.updateUserActivity(userId, (byte) 0);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean unblockUser(long userId) throws ServiceException {
        try {
            return dao.updateUserActivity(userId, (byte) 1);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changePassword(long userId, Map<String, String> fields) throws ServiceException {
        try {
            if (UserValidator.isChangePasswordFormValid(fields)) {
                String newPassword = fields.get(RequestParameter.NEW_PASSWORD);
                String encryptedPassword = Encryptor.encrypt(newPassword);
                return dao.updatePassword(userId, encryptedPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean changePhoto(long userId, String photoName) throws ServiceException {
        try {
            return (UserValidator.isPhotoNameValid(photoName) && dao.changePhoto(userId, photoName));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return dao.findAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findBlockedUsers() throws ServiceException {
        try {
            return dao.findUsersByActivity(false);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findActiveUsers() throws ServiceException {
        try {
            return dao.findUsersByActivity(true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeUserRole(long userId, String role) throws ServiceException {
        try {
            if (UserValidator.isUserRoleValid(role)) {
                UserRole userRole = UserRole.valueOf(role.toUpperCase(Locale.ROOT));
                return dao.changeUserRole(userId, userRole);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public Optional<User> updateProfile(long userId, Map<String, String> fields) throws ServiceException {
        try {
            if (UserValidator.isEditFormValid(fields)) {
                Optional<User> userOptional = dao.findUserById(userId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    if (user.getEmail().equals(fields.get(RequestParameter.EMAIL)) || dao.isEmailAvailable(fields.get(RequestParameter.EMAIL))) {
                        updateUserFields(user, fields);
                        return (dao.updateProfile(user) ? Optional.of(user) : Optional.empty());
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(long userId) throws ServiceException {
        try {
            return dao.findUserById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUsersByKeyWord(String keyWord) throws ServiceException {
        try {
            String keyWordForQuery = PERCENT_SIGN + keyWord + PERCENT_SIGN;
            return dao.findUsersByKeyWord(keyWordForQuery);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isEmailAvailable(String email) throws ServiceException {
        try {
            return (UserValidator.isEmailValid(email) && dao.isEmailAvailable(email));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void updateUserFields(User user, Map<String, String> fields) {
        String newFirstName = fields.get(RequestParameter.FIRST_NAME);
        user.setFirstName(newFirstName);

        String newLastName = fields.get(RequestParameter.LAST_NAME);
        user.setLastName(newLastName);

        LocalDate newDateOfBirth = LocalDate.parse(fields.get(RequestParameter.DATE_OF_BIRTH));
        user.setDateOfBirth(newDateOfBirth);

        String newPhoneNumber = fields.get(RequestParameter.PHONE_NUMBER);
        user.setPhoneNumber(newPhoneNumber);

        String newEmail = fields.get(RequestParameter.EMAIL);
        user.setEmail(newEmail);
    }
}
