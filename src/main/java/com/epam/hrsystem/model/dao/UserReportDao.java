package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.UserReport;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with user reports table.
 *
 * @author Aleksey Klevitov
 */
public interface UserReportDao {
    /**
     * Adds user report to the table.
     *
     * @param report UserReport object.
     * @return boolean value. True if the user report has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean add(UserReport report) throws DaoException;

    /**
     * Finds user report by id.
     *
     * @param reportId long value of user's report id.
     * @return Optional object of user report if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<UserReport> findUserReportById(long reportId) throws DaoException;

    /**
     * Finds all user reports.
     *
     * @return List object of user reports.
     * @throws DaoException if the database throws SQLException.
     */
    List<UserReport> findAllUserReports() throws DaoException;

    /**
     * Finds user reports by availability.
     *
     * @param areAvailable boolean value of reports availability.
     * @return List object of user reports.
     * @throws DaoException if the database throws SQLException.
     */
    List<UserReport> findUserReportsByAvailability(boolean areAvailable) throws DaoException;

    /**
     * Finds user reports by key word.
     *
     * @param keyWord String object. Key word used to find user reports.
     * @return List object of user reports.
     * @throws DaoException if the database throws SQLException.
     */
    List<UserReport> findUserReportsByKeyWord(String keyWord) throws DaoException;

    /**
     * Updates user report response.
     *
     * @param report UserReport object.
     * @return boolean value. True if the user report response has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateUserReportResponse(UserReport report) throws DaoException;

    /**
     * Checks if user's report exists.
     *
     * @param report UserReport object.
     * @return boolean value. True if the user report exists, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean userReportExists(UserReport report) throws DaoException;
}
