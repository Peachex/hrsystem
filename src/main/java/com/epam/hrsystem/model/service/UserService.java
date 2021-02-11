package com.epam.hrsystem.model.service;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String email, String password) throws ServiceException;

    boolean register(Map<String, String> fields) throws ServiceException;

    boolean blockUser(long userId) throws ServiceException;

    boolean unblockUser(long userId) throws ServiceException;

    boolean changePassword(long userId, String newPassword) throws ServiceException;

    boolean changePhoto(long userId, String photoName) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    List<User> findBlockedUsers() throws ServiceException;

    List<User> findNotBlockedUsers() throws ServiceException;

    boolean changeUserRole(long userId, String role) throws ServiceException;

    boolean updateProfile(long userId, Map<String, String> newFields) throws ServiceException;

}
