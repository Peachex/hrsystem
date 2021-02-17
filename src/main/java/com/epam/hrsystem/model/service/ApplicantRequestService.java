package com.epam.hrsystem.model.service;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.User;

import java.util.Map;

public interface ApplicantRequestService {
    boolean createApplicantRequest(Map<String, String> fields, User applicant) throws ServiceException;

    boolean isApplicantRequestExists(ApplicantRequest request) throws ServiceException;
}
