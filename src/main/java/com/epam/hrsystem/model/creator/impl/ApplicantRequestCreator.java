package com.epam.hrsystem.model.creator.impl;

import com.epam.hrsystem.controller.attribute.Constant;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.creator.Creator;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.ApplicantState;

import static com.epam.hrsystem.validator.ApplicantRequestValidator.isSummaryValid;

import java.util.Map;
import java.util.Optional;

public enum ApplicantRequestCreator implements Creator<ApplicantRequest> {
    INSTANCE;
    private static final ApplicantState DEFAULT_APPLICANT_STATE = ApplicantState.LEFT_REQUEST;

    @Override
    public Optional<ApplicantRequest> create(Map<String, String> fields) {
        Optional<ApplicantRequest> result = Optional.empty();
        String summary = fields.get(RequestParameter.SUMMARY).replaceAll(Constant.NEW_LINE_SYMBOL, Constant.NEW_LINE_HTML_TAG);
        if (isSummaryValid(summary)) {
            ApplicantRequest request = new ApplicantRequest(summary, DEFAULT_APPLICANT_STATE);
            result = Optional.of(request);
        }
        return result;
    }
}
