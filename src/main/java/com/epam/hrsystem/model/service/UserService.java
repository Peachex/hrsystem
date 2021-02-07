package com.epam.hrsystem.model.service;

import com.epam.hrsystem.exception.ServiceException;

import java.util.Map;

public interface UserService {
    boolean signIn(String email, String password) throws ServiceException;

    boolean signUp(Map<String, String> fields) throws ServiceException;

    boolean blockUser(long userId) throws ServiceException;

    boolean unblockUser(long userId) throws ServiceException;

    boolean changePassword(long userId, String newPassword) throws ServiceException;

    boolean changePhoto(long userId, String photoName) throws ServiceException;

    //todo changeRole, changeName
}
