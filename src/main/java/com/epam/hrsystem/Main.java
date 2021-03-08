package com.epam.hrsystem;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.impl.ApplicantRequestServiceImpl;
import com.epam.hrsystem.model.service.impl.ServiceHolder;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ServiceException {
        //fixme delete main
        ApplicantRequestService service = ServiceHolder.HOLDER.getApplicantRequestService();
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.INTERVIEW_RESULT_RATING, "9");
        fields.put(RequestParameter.INTERVIEW_RESULT_COMMENT, "BADDDD");
        fields.put(RequestParameter.APPLICANT_STATE, "PASSED");
        System.out.println(service.createInterviewResult(fields, 39, 23));
        /*ApplicantRequest applicantRequest = service.findApplicantRequestByVacancyIdAndApplicantId(39, 23).get();
        System.out.println(applicantRequest);*/

    }
}
