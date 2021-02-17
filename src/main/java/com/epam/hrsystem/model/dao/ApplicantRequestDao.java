package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.ApplicantRequest;

public interface ApplicantRequestDao {
    boolean add(ApplicantRequest request) throws DaoException;

    boolean applicantRequestExists(ApplicantRequest request) throws DaoException;
}
