package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.dao.UserReportDao;
import com.epam.hrsystem.model.dao.impl.UserDaoImpl;
import com.epam.hrsystem.model.dao.impl.UserReportDaoImpl;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserReport;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.factory.impl.UserReportFactory;
import com.epam.hrsystem.model.service.UserReportService;
import com.epam.hrsystem.validator.UserReportValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * UserReportService implementation.
 *
 * @author Aleksey Klevitov
 */
public class UserReportServiceImpl implements UserReportService {
    private static final UserReportDao userReportDao = UserReportDaoImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final String PERCENT_SIGN = "%";
    private static final EntityFactory<UserReport> userReportFactory = UserReportFactory.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile UserReportService instance;

    /**
     * Constructs an UserReportServiceImpl object.
     */
    private UserReportServiceImpl() {
    }

    /**
     * Returns a UserReportService object.
     */
    public static UserReportService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new UserReportServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean createUserReport(Map<String, String> fields, long userId) throws ServiceException {
        Optional<UserReport> reportOptional = userReportFactory.create(fields);
        try {
            if (reportOptional.isPresent()) {
                UserReport report = reportOptional.get();
                Optional<User> userOptional = UserDaoImpl.getInstance().findUserById(userId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    report.setUser(user);
                    return (!userReportDao.userReportExists(report) && userReportDao.add(report));
                }
            }
        } catch (DaoException | NumberFormatException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public List<UserReport> findAllUserReports() throws ServiceException {
        try {
            List<UserReport> reports = userReportDao.findAllUserReports();
            for (UserReport report : reports) {
                updateReportUser(report);
            }
            return reports;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserReport> findAvailableUserReports() throws ServiceException {
        try {
            List<UserReport> reports = userReportDao.findUserReportsByAvailability(true);
            for (UserReport report : reports) {
                updateReportUser(report);
            }
            return reports;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserReport> findDeletedUserReports() throws ServiceException {
        try {
            List<UserReport> reports = userReportDao.findUserReportsByAvailability(false);
            for (UserReport report : reports) {
                updateReportUser(report);
            }
            return reports;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserReport> findUserReportsByKeyWord(String keyWord) throws ServiceException {
        try {
            String keyWordForQuery = PERCENT_SIGN + keyWord + PERCENT_SIGN;
            List<UserReport> reports = userReportDao.findUserReportsByKeyWord(keyWordForQuery);
            for (UserReport report : reports) {
                updateReportUser(report);
            }
            return reports;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createResponse(long reportId, String response) throws ServiceException {
        try {
            if (UserReportValidator.isResponseValid(response)) {
                Optional<UserReport> reportOptional = userReportDao.findUserReportById(reportId);
                if (reportOptional.isPresent()) {
                    UserReport report = reportOptional.get();
                    if (report.getResponse() == null) {
                        report.setResponse(response);
                        return userReportDao.updateUserReportResponse(report);
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public Optional<UserReport> findUserReportById(long reportId) throws ServiceException {
        try {
            Optional<UserReport> reportOptional = userReportDao.findUserReportById(reportId);
            if (reportOptional.isPresent()) {
                UserReport report = reportOptional.get();
                if (updateReportUser(report)) {
                    reportOptional = Optional.of(report);
                }
            }
            return reportOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean updateReportUser(UserReport report) throws DaoException {
        boolean result = false;
        Optional<User> userOptional = userDao.findUserById(report.getUser().getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            report.setUser(user);
            result = true;
        }
        return result;
    }
}
