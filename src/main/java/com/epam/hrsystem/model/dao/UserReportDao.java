package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.UserReport;

import java.util.List;
import java.util.Optional;

public interface UserReportDao {
    boolean add(UserReport report) throws DaoException;

    Optional<UserReport> findUserReportById(long reportId) throws DaoException;

    boolean updateUserReportAvailability(long reportId, byte availabilityValue) throws DaoException;

    List<UserReport> findAllUserReports() throws DaoException;

    List<UserReport> findUserReportsByAvailability(boolean areAvailable) throws DaoException;

    boolean updateUserReportResponse(UserReport report) throws DaoException;

    boolean userReportExists(UserReport report) throws DaoException;
}
