package com.epam.hrsystem.model.service;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.UserReport;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface provides actions on user report.
 *
 * @author Aleksey Klevtiov
 */
public interface UserReportService {
    /**
     * Creates user report.
     *
     * @param fields Map object with user report's fields with RequestParameter's constants as keys inside.
     * @param userId long value of user's id.
     * @return boolean value. True if the user report has been created, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean createUserReport(Map<String, String> fields, long userId) throws ServiceException;

    /**
     * Finds all user reports.
     *
     * @return List objcet of user reports.
     * @throws ServiceException if an error occurs while processing.
     */
    List<UserReport> findAllUserReports() throws ServiceException;

    /**
     * Finds available user reports.
     *
     * @return List object of available user reports.
     * @throws ServiceException if an error occurs while processing.
     */
    List<UserReport> findAvailableUserReports() throws ServiceException;

    /**
     * Finds deleted user reports.
     *
     * @return List object of deleted user reports.
     * @throws ServiceException if an error occurs while processing.
     */
    List<UserReport> findDeletedUserReports() throws ServiceException;

    /**
     * Finds user reports by key word.
     *
     * @param keyWord String object. Key word used to find user reports.
     * @return List object of user reports.
     * @throws ServiceException if an error occurs while processing.
     */
    List<UserReport> findUserReportsByKeyWord(String keyWord) throws ServiceException;

    /**
     * Creates user report response.
     *
     * @param reportId long value of report's id.
     * @param response String object. Contains user report response.
     * @return boolean value. True if the user report response has been created, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean createResponse(long reportId, String response) throws ServiceException;

    /**
     * Finds user report by id.
     *
     * @param reportId long value of report's id.
     * @return Optional object of user report if exists, Optional.empty() otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<UserReport> findUserReportById(long reportId) throws ServiceException;
}
