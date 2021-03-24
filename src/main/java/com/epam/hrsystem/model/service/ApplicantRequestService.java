package com.epam.hrsystem.model.service;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ApplicantRequestService {
    boolean createApplicantRequest(Map<String, String> fields, User applicant) throws ServiceException;

    List<ApplicantRequest> findApplicantRequestsByVacancyId(long vacancyId) throws ServiceException;

    List<ApplicantRequest> findApplicantRequestsByApplicantId(long applicantId) throws ServiceException;

    Optional<ApplicantRequest> findApplicantRequestByVacancyIdAndApplicantId(long vacancyId, long applicantId) throws ServiceException;

    boolean createInterviewResult(Map<String, String> fields, long vacancyId, long applicantId) throws ServiceException;

    boolean scheduleTechnicalInterview(String technicalInterviewDateStr, long vacancyId, long applicantId) throws ServiceException;

    boolean applicantRequestExists(ApplicantRequest request) throws ServiceException;
}
