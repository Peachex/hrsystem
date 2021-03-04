package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.dao.InterviewResultDao;
import com.epam.hrsystem.model.dao.impl.InterviewResultDaoImpl;
import com.epam.hrsystem.model.entity.ApplicantState;
import com.epam.hrsystem.model.entity.InterviewResult;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.factory.impl.ApplicantRequestFactory;
import com.epam.hrsystem.model.dao.ApplicantRequestDao;
import com.epam.hrsystem.model.dao.impl.ApplicantRequestDaoImpl;
import com.epam.hrsystem.model.dao.impl.SqlQuery;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.factory.impl.InterviewResultFactory;
import com.epam.hrsystem.model.service.ApplicantRequestService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public enum ApplicantRequestServiceImpl implements ApplicantRequestService {
    INSTANCE;
    private static final ApplicantRequestDao applicantRequestDao = ApplicantRequestDaoImpl.INSTANCE;
    private static final InterviewResultDao interviewResultDao = InterviewResultDaoImpl.INSTANCE;

    @Override
    public boolean createApplicantRequest(Map<String, String> fields, User applicant) throws ServiceException {
        boolean result = false;
        EntityFactory<ApplicantRequest> factory = new ApplicantRequestFactory();
        Optional<ApplicantRequest> requestOptional = factory.create(fields);
        try {
            if (requestOptional.isPresent()) {
                long vacancyId = Long.parseLong(fields.get(RequestParameter.VACANCY_ID));
                Optional<Vacancy> vacancyOptional = VacancyServiceImpl.INSTANCE.findVacancyById(vacancyId);
                if (vacancyOptional.isPresent()) {
                    ApplicantRequest request = requestOptional.get();
                    request.setApplicant(applicant);
                    request.setVacancy(vacancyOptional.get());
                    if (!isApplicantRequestExists(request)) {
                        result = applicantRequestDao.add(request);
                    }
                }
            }
        } catch (DaoException | NumberFormatException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<ApplicantRequest> findApplicantRequestsByVacancyId(long vacancyId) throws ServiceException {
        List<ApplicantRequest> applicantRequests;
        try {
            applicantRequests = applicantRequestDao.findApplicantRequestsByIdAndSqlQuery(vacancyId, SqlQuery.SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return applicantRequests;
    }

    @Override
    public List<ApplicantRequest> findApplicantRequestsByApplicant(long applicantId) throws ServiceException {
        List<ApplicantRequest> applicantRequests;
        try {
            applicantRequests = applicantRequestDao.findApplicantRequestsByIdAndSqlQuery(applicantId, SqlQuery.SQL_SELECT_APPLICANT_REQUESTS_BY_APPLICANT_ID);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return applicantRequests;
    }

    @Override
    public Optional<ApplicantRequest> findApplicantRequestByVacancyIdAndApplicantId(long vacancyId, long applicantId) throws ServiceException {
        Optional<ApplicantRequest> applicantRequest;
        try {
            applicantRequest = applicantRequestDao.findApplicantRequestByVacancyIdAndApplicantId(vacancyId, applicantId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return applicantRequest;
    }

    @Override
    public boolean createInterviewResult(Map<String, String> fields, long vacancyId, long applicantId) throws ServiceException {
        boolean result = false;
        Optional<ApplicantRequest> applicantRequestOptional;
        try {
            applicantRequestOptional = applicantRequestDao.findApplicantRequestByVacancyIdAndApplicantId(vacancyId, applicantId);
            if (applicantRequestOptional.isPresent()) {
                ApplicantRequest applicantRequest = applicantRequestOptional.get();
                EntityFactory<InterviewResult> factory = new InterviewResultFactory();
                Optional<InterviewResult> interviewResultOptional = factory.create(fields);
                if (interviewResultOptional.isPresent()) {
                    String newApplicantState = fields.get(RequestParameter.APPLICANT_STATE);
                    InterviewResult interviewResult = interviewResultOptional.get();
                    if (updateInterviewResult(applicantRequest, interviewResult, newApplicantState)) {
                        if (interviewResultDao.add(interviewResult)) {
                            result = applicantRequestDao.updateApplicantRequest(applicantRequest);
                        }
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean isApplicantRequestExists(ApplicantRequest request) throws ServiceException {
        boolean result;
        try {
            result = applicantRequestDao.applicantRequestExists(request);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    private boolean updateInterviewResult(ApplicantRequest applicantRequest, InterviewResult interviewResult, String state) {
        boolean result = true;
        if (applicantRequest.getBasicInterviewResult() == null) {
            applicantRequest.setBasicInterviewResult(interviewResult);
        } else {
            if (applicantRequest.getTechnicalInterviewResult() == null) {
                applicantRequest.setTechnicalInterviewResult(interviewResult);
            } else {
                result = false;
            }
        }
        if (result) {
            applicantRequest.setApplicantState(ApplicantState.valueOf(state));
        }
        return result;
    }
}
