package com.epam.hrsystem;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.service.impl.ApplicantRequestServiceImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ServiceException {
        ApplicantRequestServiceImpl service = ApplicantRequestServiceImpl.INSTANCE;
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.INTERVIEW_RESULT_RATING, "9");
        fields.put(RequestParameter.INTERVIEW_RESULT_COMMENT,"Good");
        fields.put(RequestParameter.APPLICANT_STATE, "READY_FOR_TECHNICAL_INTERVIEW");
        service.createInterviewResult(fields,37, 23);
    }
}
