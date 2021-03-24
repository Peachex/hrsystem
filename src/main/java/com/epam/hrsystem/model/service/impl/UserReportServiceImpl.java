package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.dao.UserReportDao;
import com.epam.hrsystem.model.dao.impl.DaoHolder;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserReport;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.factory.impl.FactoryHolder;
import com.epam.hrsystem.model.service.UserReportService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserReportServiceImpl implements UserReportService {
    private static final UserReportDao dao = DaoHolder.HOLDER.getUserReportDao();

    @Override
    public boolean createUserReport(Map<String, String> fields, long userId) throws ServiceException {
        boolean result = false;
        EntityFactory<UserReport> factory = FactoryHolder.HOLDER.getUserReportFactory();
        Optional<UserReport> reportOptional = factory.create(fields);
        try {
            if (reportOptional.isPresent()) {
                UserReport report = reportOptional.get();
                Optional<User> userOptional = DaoHolder.HOLDER.getUserDao().findUserById(userId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    report.setUser(user);
                    if (!userReportExists(report)) {
                        result = dao.add(report);
                    }
                }
            }
        } catch (DaoException | NumberFormatException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean deleteReport(long reportId) throws ServiceException {
        boolean result = false;
        try {
            Optional<UserReport> report = dao.findUserReportById(reportId);
            if (report.isPresent()) {
                result = dao.updateUserReportAvailability(reportId, (byte) 0);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean restoreReport(long reportId) throws ServiceException {
        boolean result = false;
        try {
            Optional<UserReport> report = dao.findUserReportById(reportId);
            if (report.isPresent()) {
                result = dao.updateUserReportAvailability(reportId, (byte) 1);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<UserReport> findAllUserReports() throws ServiceException {
        try {
            List<UserReport> reports = dao.findAllUserReports();
            return reports;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserReport> findAvailableUserReports() throws ServiceException {
        try {
            List<UserReport> reports = dao.findUserReportsByAvailability(true);
            return reports;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserReport> findDeletedUserReports() throws ServiceException {
        try {
            List<UserReport> reports = dao.findUserReportsByAvailability(false);
            return reports;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean userReportExists(UserReport report) throws ServiceException {
        boolean result;
        try {
            result = dao.userReportExists(report);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
