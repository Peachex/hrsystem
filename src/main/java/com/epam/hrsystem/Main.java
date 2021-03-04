package com.epam.hrsystem;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.service.impl.ApplicantRequestServiceImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ServiceException {
        //fixme delete main
        ApplicantRequestServiceImpl service = ApplicantRequestServiceImpl.INSTANCE;
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.INTERVIEW_RESULT_RATING, "2");
        fields.put(RequestParameter.INTERVIEW_RESULT_COMMENT,"BAD");
        fields.put(RequestParameter.APPLICANT_STATE, "FAILED");
        service.createInterviewResult(fields,39, 23);
    }
}
