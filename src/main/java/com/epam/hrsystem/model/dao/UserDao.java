package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.User;

import java.util.Optional;

public interface UserDao {
    boolean isEmailAvailable(String email) throws DaoException;

    Optional<String> findPasswordByEmail(String email) throws DaoException;

    boolean add(User user, String password) throws DaoException;
}
