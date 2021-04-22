package com.epam.hrsystem.model.factory.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.entity.InterviewResult;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.hrsystem.validator.InterviewResultValidator.isInterviewResultFormValid;

/**
 * EntityFactory implementation used to create an InterviewResult object.
 *
 * @author Aleksey Klevitov
 */
public class InterviewResultFactory implements EntityFactory<InterviewResult> {
    private static final Lock locker = new ReentrantLock();
    private static EntityFactory<InterviewResult> instance;

    /**
     * Constructs an InterviewResultFactory object.
     */
    private InterviewResultFactory() {
    }

    /**
     * Returns an EntityFactory object.
     */
    public static EntityFactory<InterviewResult> getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new InterviewResultFactory();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public Optional<InterviewResult> create(Map<String, String> fields) {
        Optional<InterviewResult> result = Optional.empty();
        if (isInterviewResultFormValid(fields)) {
            byte rating = Byte.parseByte(fields.get(RequestParameter.INTERVIEW_RESULT_RATING));
            String comment = fields.get(RequestParameter.INTERVIEW_RESULT_COMMENT);
            InterviewResult interviewResult = new InterviewResult(rating, comment);
            result = Optional.of(interviewResult);
        }
        return result;
    }
}
