package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.creator.Creator;
import com.epam.hrsystem.model.creator.impl.UserCreator;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.dao.impl.UserDaoImpl;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.util.Encryptor;
import com.epam.hrsystem.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final UserDao dao = UserDaoImpl.INSTANCE;

    @Override
    public boolean signIn(String email, String password) throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.isEmailValid(email) && UserValidator.isPasswordValid(password)) {
                if (!dao.isEmailAvailable(email)) {
                    Optional<String> passwordFromDatabase = dao.findPasswordByEmail(email);
                    if (passwordFromDatabase.isPresent()) {
                        result = Encryptor.check(password, passwordFromDatabase.get());
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean signUp(Map<String, String> fields) throws ServiceException {
        boolean result = false;
        Creator<User> creator = new UserCreator();
        Optional<User> user = creator.create(fields);
        try {
            String password = fields.get(RequestParameter.PASSWORD);
            if (UserValidator.isPasswordValid(password) && user.isPresent()) {
                String passwordRepeat = fields.get(RequestParameter.PASSWORD_REPEAT);
                if (UserValidator.isPasswordValid(password, passwordRepeat)) {
                    String encryptedPassword = Encryptor.encrypt(password);
                    result = dao.add(user.get(), encryptedPassword);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean blockUser(long userId) throws ServiceException {
        return false;
    }

    @Override
    public boolean unblockUser(long userId) throws ServiceException {
        return false;
    }

    @Override
    public boolean changePassword(long userId, String newPassword) throws ServiceException {
        return false;
    }

    @Override
    public boolean changePhoto(long userId, String photoName) throws ServiceException {
        return false;
    }
}
