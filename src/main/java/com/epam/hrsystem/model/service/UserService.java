package com.epam.hrsystem.model.service;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 *
 * @author Aleksey Klevtiov
 */
public interface UserService {
    /**
     * Checks if the input parameters are valid, and then, if they are, gives a permission to user to login.
     *
     * @param email    String object of user's email.
     * @param password String object of user's password.
     * @return Optional object of user if exists, Optional.empty() otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<User> login(String email, String password) throws ServiceException;

    /**
     * Checks if the input parameters are valid, and then, if they are, gives a permission to user to register.
     *
     * @param fields Map object with user's fields with RequestParameter's constants as keys inside.
     * @return boolean value. True if the user has been registered, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean register(Map<String, String> fields) throws ServiceException;

    /**
     * Blocks user.
     *
     * @param userId long value of user' id.
     * @return boolean value. True if the user has been blocked, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean blockUser(long userId) throws ServiceException;

    /**
     * Unblocks user.
     *
     * @param userId long value of user' id.
     * @return boolean value. True if the user has been unblocked, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean unblockUser(long userId) throws ServiceException;

    /**
     * Changes user's password.
     *
     * @param userId long value of user' id.
     * @param fields Map object with user's fields with RequestParameter's constants as keys inside.
     * @return boolean value. True if the user's password has been changed, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean changePassword(long userId, Map<String, String> fields) throws ServiceException;

    /**
     * Changes user's photo name.
     *
     * @param userId    long value of user' id.
     * @param photoName String object of user's photo name.
     * @return boolean value. True if the user's photo name has been changed, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean changePhoto(long userId, String photoName) throws ServiceException;

    /**
     * Finds all users.
     *
     * @return List object of users.
     * @throws ServiceException if an error occurs while processing.
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Finds blocked users.
     *
     * @return List object of blocked users.
     * @throws ServiceException if an error occurs while processing.
     */
    List<User> findBlockedUsers() throws ServiceException;

    /**
     * Finds active users.
     *
     * @return List object of active users.
     * @throws ServiceException if an error occurs while processing.
     */
    List<User> findActiveUsers() throws ServiceException;

    /**
     * Changes user's role.
     *
     * @param userId long value of user' id.
     * @param role   UserRole object of user's role.
     * @return boolean value. True if the user's role has been changed, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean changeUserRole(long userId, String role) throws ServiceException;

    /**
     * Updates user's profile.
     *
     * @param userId    long value of user' id.
     * @param newFields Map object with new user's fields with RequestParameter's constants as keys inside.
     * @return Optional object of user if exists, Optional.empty() otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<User> updateProfile(long userId, Map<String, String> newFields) throws ServiceException;

    /**
     * Finds user by id.
     *
     * @param userId long value of user' id.
     * @return Optional object of user if exists, Optional.empty() otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<User> findUserById(long userId) throws ServiceException;

    /**
     * Finds users by key word.
     *
     * @param keyWord String object. Key word used to find users.
     * @return List object of users.
     * @throws ServiceException if an error occurs while processing.
     */
    List<User> findUsersByKeyWord(String keyWord) throws ServiceException;

    /**
     * Checks if user's email is available.
     *
     * @param email String object of user's email.
     * @return boolean value. True if the user's email exists, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean isEmailAvailable(String email) throws ServiceException;
}
