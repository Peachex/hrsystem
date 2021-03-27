package com.epam.hrsystem.model.service;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.UserReport;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserReportService {
    boolean createUserReport(Map<String, String> fields, long userId) throws ServiceException;

    boolean deleteReport(long reportId) throws ServiceException;

    boolean restoreReport(long reportId) throws ServiceException;

    List<UserReport> findAllUserReports() throws ServiceException;

    List<UserReport> findAvailableUserReports() throws ServiceException;

    List<UserReport> findDeletedUserReports() throws ServiceException;

    List<UserReport> findUserReportsByKeyWord(String keyWord) throws ServiceException;

    boolean createResponse(long reportId, String response) throws ServiceException;

    Optional<UserReport> findUserReportById(long reportId) throws ServiceException;

    boolean userReportExists(UserReport report) throws ServiceException;
}
