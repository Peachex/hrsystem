package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserRole;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with users table.
 *
 * @author Aleksey Klevitov
 */
public interface UserDao {
    /**
     * Checks if email is available.
     *
     * @param email String object of user's email.
     * @return boolean value. True if the email is available, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean isEmailAvailable(String email) throws DaoException;

    /**
     * Finds user by email.
     *
     * @param email String object of user's email.
     * @return Optional object of user if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Finds password by user's email.
     *
     * @param email String object of user's email.
     * @return Optional object of user's password if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<String> findPasswordByEmail(String email) throws DaoException;

    /**
     * Adds user to the table.
     *
     * @param user     User object.
     * @param password String object of user's password.
     * @return boolean value. True if the user has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean add(User user, String password) throws DaoException;

    /**
     * Updates user's activity.
     *
     * @param userId        long value of user's id.
     * @param activityValue boolean value of user's activity.
     * @return boolean value. True if the user's activity has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateUserActivity(long userId, byte activityValue) throws DaoException;

    /**
     * Updates user's password.
     *
     * @param userId      long value of user's id.
     * @param newPassword String object of new user's password.
     * @return boolean value. True if the user's password has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updatePassword(long userId, String newPassword) throws DaoException;

    /**
     * Finds users by activity.
     *
     * @param areActive boolean value of users' activity.
     * @return List object of users.
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findUsersByActivity(boolean areActive) throws DaoException;

    /**
     * Finds users by key word.
     *
     * @param keyWord String object. Key word used to find users.
     * @return List object of users.
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findUsersByKeyWord(String keyWord) throws DaoException;

    /**
     * Finds all users.
     *
     * @return List object of users.
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findAllUsers() throws DaoException;

    /**
     * Changes user's role.
     *
     * @param userId long value of user's id.
     * @param role   UserRole object of user's role.
     * @return boolean value. True if the user's role has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean changeUserRole(long userId, UserRole role) throws DaoException;

    /**
     * Updates user's profile.
     *
     * @param user User object.
     * @return boolean value. True if the user's profile has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateProfile(User user) throws DaoException;

    /**
     * Finds user by id.
     *
     * @param userId long value of user's id.
     * @return Optional object of user if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<User> findUserById(long userId) throws DaoException;

    /**
     * Changes user's photo name.
     *
     * @param userId    long value of user's id.
     * @param photoName String object of user's photo name.
     * @return boolean value. True if the user's photo name has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean changePhoto(long userId, String photoName) throws DaoException;
}
