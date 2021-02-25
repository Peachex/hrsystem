package com.epam.hrsystem.model.creator.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.creator.Creator;
import com.epam.hrsystem.model.entity.InterviewResult;

import java.util.Map;
import java.util.Optional;

import static com.epam.hrsystem.validator.InterviewResultValidator.isInterviewResultFormValid;

public enum InterviewResultCreator implements Creator<InterviewResult> {
    INSTANCE;

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
