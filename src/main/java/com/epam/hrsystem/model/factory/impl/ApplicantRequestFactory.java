package com.epam.hrsystem.model.factory.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.ApplicantState;

import static com.epam.hrsystem.validator.ApplicantRequestValidator.isSummaryValid;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * EntityFactory implementation used to create an ApplicantRequest object.
 *
 * @author Aleksey Klevitov
 */
public class ApplicantRequestFactory implements EntityFactory<ApplicantRequest> {
    private static final ApplicantState DEFAULT_APPLICANT_STATE = ApplicantState.LEFT_REQUEST;
    private static final Lock locker = new ReentrantLock();
    private static EntityFactory<ApplicantRequest> instance;

    /**
     * Constructs an ApplicantRequestFactory object.
     */
    private ApplicantRequestFactory() {
    }

    /**
     * Returns an EntityFactory object.
     */
    public static EntityFactory<ApplicantRequest> getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new ApplicantRequestFactory();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public Optional<ApplicantRequest> create(Map<String, String> fields) {
        String summary = fields.get(RequestParameter.SUMMARY);
        return (isSummaryValid(summary) ? Optional.of(new ApplicantRequest(summary, DEFAULT_APPLICANT_STATE)) :
                Optional.empty());
    }
}
