package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
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
    private static final UserReportDao dao = UserReportDaoImpl.getInstance();
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
                    return (!dao.userReportExists(report) && dao.add(report));
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
            return dao.findAllUserReports();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserReport> findAvailableUserReports() throws ServiceException {
        try {
            return dao.findUserReportsByAvailability(true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserReport> findDeletedUserReports() throws ServiceException {
        try {
            return dao.findUserReportsByAvailability(false);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserReport> findUserReportsByKeyWord(String keyWord) throws ServiceException {
        try {
            String keyWordForQuery = PERCENT_SIGN + keyWord + PERCENT_SIGN;
            return dao.findUserReportsByKeyWord(keyWordForQuery);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createResponse(long reportId, String response) throws ServiceException {
        try {
            if (UserReportValidator.isResponseValid(response)) {
                Optional<UserReport> reportOptional = dao.findUserReportById(reportId);
                if (reportOptional.isPresent()) {
                    UserReport report = reportOptional.get();
                    if (report.getResponse() == null) {
                        report.setResponse(response);
                        return dao.updateUserReportResponse(report);
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
            return dao.findUserReportById(reportId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
