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
import com.epam.hrsystem.validator.UserReportValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserReportServiceImpl implements UserReportService {
    private static final UserReportDao dao = DaoHolder.HOLDER.getUserReportDao();
    private static final String PERCENT_SIGN = "%";

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
    public List<UserReport> findUserReportsByKeyWord(String keyWord) throws ServiceException {
        try {
            String keyWordForQuery = PERCENT_SIGN + keyWord + PERCENT_SIGN;
            List<UserReport> reports = dao.findUserReportsByKeyWord(keyWordForQuery);
            return reports;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createResponse(long reportId, String response) throws ServiceException {
        boolean result = false;
        try {
            if (UserReportValidator.isResponseValid(response)) {
                Optional<UserReport> reportOptional = dao.findUserReportById(reportId);
                if (reportOptional.isPresent()) {
                    UserReport report = reportOptional.get();
                    if (report.getResponse() == null) {
                        report.setResponse(response);
                        result = dao.updateUserReportResponse(report);
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Optional<UserReport> findUserReportById(long reportId) throws ServiceException {
        Optional<UserReport> report;
        try {
            report = dao.findUserReportById(reportId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return report;
    }

    private boolean userReportExists(UserReport report) throws ServiceException {
        boolean result;
        try {
            result = dao.userReportExists(report);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
