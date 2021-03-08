package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    //fixme delete unnecessary functions
    boolean isEmailAvailable(String email) throws DaoException;

    Optional<User> login(String email) throws DaoException;

    Optional<String> findPasswordByEmail(String email) throws DaoException;

    boolean add(User user, String password) throws DaoException;

    Optional<Byte> findUserActivity(String email) throws DaoException;

    boolean updateUserActivity(long userId, byte activityValue) throws DaoException;

    boolean updatePassword(long userId, String newPassword) throws DaoException;

    List<User> findUsersByActivity(boolean areActive) throws DaoException;

    List<User> findAllUsers() throws DaoException;

    boolean changeUserRole(long userId, UserRole role) throws DaoException;

    boolean updateProfile(User user) throws DaoException;

    Optional<User> findUserById(long userId) throws DaoException;

    boolean changePhoto(long userId, String photoName) throws DaoException;
}
