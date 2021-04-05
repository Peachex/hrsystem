package com.epam.hrsystem.model.factory.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.ApplicantState;

import static com.epam.hrsystem.validator.ApplicantRequestValidator.isSummaryValid;

import java.util.Map;
import java.util.Optional;

/**
 * EntityFactory implementation used to create an ApplicantRequest object.
 *
 * @author Aleksey Klevitov
 */
public class ApplicantRequestFactory implements EntityFactory<ApplicantRequest> {
    private static final ApplicantState DEFAULT_APPLICANT_STATE = ApplicantState.LEFT_REQUEST;

    /**
     * Constructs an ApplicantRequestFactory object.
     */
    ApplicantRequestFactory() {
    }

    @Override
    public Optional<ApplicantRequest> create(Map<String, String> fields) {
        Optional<ApplicantRequest> result = Optional.empty();
        String summary = fields.get(RequestParameter.SUMMARY);
        if (isSummaryValid(summary)) {
            ApplicantRequest request = new ApplicantRequest(summary, DEFAULT_APPLICANT_STATE);
            result = Optional.of(request);
        }
        return result;
    }
}
