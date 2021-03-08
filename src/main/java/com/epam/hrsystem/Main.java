package com.epam.hrsystem;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.impl.ServiceHolder;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ServiceException {
        //fixme delete main
        /*ApplicantRequestService service = ServiceHolder.HOLDER.getApplicantRequestService();
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.INTERVIEW_RESULT_RATING, "9");
        fields.put(RequestParameter.INTERVIEW_RESULT_COMMENT, "BADDDD");
        fields.put(RequestParameter.APPLICANT_STATE, "PASSED");
        System.out.println(service.createInterviewResult(fields, 39, 23));*/
        /*ApplicantRequest applicantRequest = service.findApplicantRequestByVacancyIdAndApplicantId(39, 23).get();
        System.out.println(applicantRequest);*/
        UserService userService = ServiceHolder.HOLDER.getUserService();
        List<User> users = userService.findAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
