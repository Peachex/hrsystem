package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.creator.Creator;
import com.epam.hrsystem.model.creator.impl.ApplicantRequestCreator;
import com.epam.hrsystem.model.dao.ApplicantRequestDao;
import com.epam.hrsystem.model.dao.impl.ApplicantRequestDaoImpl;
import com.epam.hrsystem.model.dao.impl.SqlQuery;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.service.ApplicantRequestService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public enum ApplicantRequestServiceImpl implements ApplicantRequestService {
    INSTANCE;
    private static final ApplicantRequestDao dao = ApplicantRequestDaoImpl.INSTANCE;

    @Override
    public boolean createApplicantRequest(Map<String, String> fields, User applicant) throws ServiceException {
        boolean result = false;
        Creator<ApplicantRequest> creator = ApplicantRequestCreator.INSTANCE;
        Optional<ApplicantRequest> requestOptional = creator.create(fields);
        try {
            if (requestOptional.isPresent()) {
                long vacancyId = Long.parseLong(fields.get(RequestParameter.VACANCY_ID));
                Optional<Vacancy> vacancyOptional = VacancyServiceImpl.INSTANCE.findVacancyById(vacancyId);
                if (vacancyOptional.isPresent()) {
                    ApplicantRequest request = requestOptional.get();
                    request.setApplicant(applicant);
                    request.setVacancy(vacancyOptional.get());
                    if (!isApplicantRequestExists(request)) {
                        result = dao.add(request);
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
            applicantRequests = dao.findApplicantRequestsByVacancyId(vacancyId, SqlQuery.SQL_SELECT_APPLICANT_REQUESTS_BY_VACANCY_ID);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return applicantRequests;
    }

    @Override
    public List<ApplicantRequest> findApplicantRequestsByApplicant(long applicantId) throws ServiceException {
        List<ApplicantRequest> applicantRequests;
        try {
            applicantRequests = dao.findApplicantRequestsByVacancyId(applicantId, SqlQuery.SQL_SELECT_APPLICANT_REQUESTS_BY_APPLICANT_ID);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return applicantRequests;
    }

    @Override
    public Optional<ApplicantRequest> findApplicantRequestByVacancyIdAndApplicantId(long vacancyId, long applicantId) throws ServiceException {
        Optional<ApplicantRequest> applicantRequest;
        try {
            applicantRequest = dao.findApplicantRequestByVacancyIdAndApplicantId(vacancyId, applicantId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return applicantRequest;
    }

    @Override
    public boolean isApplicantRequestExists(ApplicantRequest request) throws ServiceException {
        boolean result;
        try {
            result = dao.applicantRequestExists(request);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
