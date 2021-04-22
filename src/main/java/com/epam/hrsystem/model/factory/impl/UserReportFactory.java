package com.epam.hrsystem.model.factory.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.entity.UserReport;
import com.epam.hrsystem.model.factory.EntityFactory;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.hrsystem.validator.UserReportValidator.isUserReportFormValid;

/**
 * EntityFactory implementation used to create UserReport object.
 *
 * @author Aleksey Klevitov
 */
public class UserReportFactory implements EntityFactory<UserReport> {
    private static final boolean DEFAULT_AVAILABILITY_VALUE = true;
    private static final Lock locker = new ReentrantLock();
    private static EntityFactory<UserReport> instance;

    /**
     * Constructs a UserReportFactory object.
     */
    private UserReportFactory() {
    }

    /**
     * Returns an EntityFactory object.
     */
    public static EntityFactory<UserReport> getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new UserReportFactory();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public Optional<UserReport> create(Map<String, String> fields) {
        Optional<UserReport> result = Optional.empty();
        if (isUserReportFormValid(fields)) {
            String subject = fields.get(RequestParameter.USER_REPORT_SUBJECT);
            String comment = fields.get(RequestParameter.USER_REPORT_COMMENT);
            LocalDate creationDate = LocalDate.now();
            UserReport report = new UserReport(DEFAULT_AVAILABILITY_VALUE, subject, comment, creationDate);
            result = Optional.of(report);
        }
        return result;
    }
}
