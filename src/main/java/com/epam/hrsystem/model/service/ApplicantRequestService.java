package com.epam.hrsystem.model.service;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface provides actions on applicant request.
 *
 * @author Aleksey Klevtiov
 */
public interface ApplicantRequestService {
    /**
     * Creates applicant request.
     *
     * @param fields    Map object with applicant request's fields with RequestParameter's constants as keys inside.
     * @param applicant Applicant object.
     * @return boolean value. True if the applicant request has been created, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean createApplicantRequest(Map<String, String> fields, User applicant) throws ServiceException;

    /**
     * Finds applicant requests by vacancy id.
     *
     * @param vacancyId long value of vacancy's id.
     * @return List object of applicant requests.
     * @throws ServiceException if an error occurs while processing.
     */
    List<ApplicantRequest> findApplicantRequestsByVacancyId(long vacancyId) throws ServiceException;

    /**
     * Finds applicant requests by applicant id.
     *
     * @param applicantId long value of applicant's id.
     * @return List object of applicant requests.
     * @throws ServiceException if an error occurs while processing.
     */
    List<ApplicantRequest> findApplicantRequestsByApplicantId(long applicantId) throws ServiceException;

    /**
     * Finds applicant request by vacancy id and applicant id.
     *
     * @param vacancyId   long value of vacancy's id.
     * @param applicantId long value of applicant's id.
     * @return Optional object of applicant request if exists, Optional.empty() otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<ApplicantRequest> findApplicantRequestByVacancyIdAndApplicantId(long vacancyId, long applicantId) throws ServiceException;

    /**
     * Creates an interview result.
     *
     * @param fields      Map object with interview result's fields with RequestParameter's constants as keys inside.
     * @param vacancyId   long value of vacancy's id.
     * @param applicantId long value of applicant's id.
     * @return boolean value. True if the interview result has been created, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean createInterviewResult(Map<String, String> fields, long vacancyId, long applicantId) throws ServiceException;

    /**
     * Schedules technical interview.
     *
     * @param technicalInterviewDateStr String object of technical interview's date.
     * @param vacancyId                 long value of vacancy's id.
     * @param applicantId               long value of applicant's id.
     * @return boolean value. True if technical interview has been scheduled, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean scheduleTechnicalInterview(String technicalInterviewDateStr, long vacancyId, long applicantId) throws ServiceException;
}
