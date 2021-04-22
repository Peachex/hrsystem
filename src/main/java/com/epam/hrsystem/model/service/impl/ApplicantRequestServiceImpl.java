package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.dao.InterviewResultDao;
import com.epam.hrsystem.model.dao.impl.ApplicantRequestDaoImpl;
import com.epam.hrsystem.model.dao.impl.InterviewResultDaoImpl;
import com.epam.hrsystem.model.entity.ApplicantState;
import com.epam.hrsystem.model.entity.InterviewResult;
import com.epam.hrsystem.model.entity.InterviewType;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.dao.ApplicantRequestDao;
import com.epam.hrsystem.model.entity.ApplicantRequest;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.factory.impl.ApplicantRequestFactory;
import com.epam.hrsystem.model.factory.impl.InterviewResultFactory;
import com.epam.hrsystem.model.service.ApplicantRequestService;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.validator.ApplicantRequestValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ApplicantRequestService implementation.
 *
 * @author Aleksey Klevitov
 */
public class ApplicantRequestServiceImpl implements ApplicantRequestService {
    private static final ApplicantRequestDao applicantRequestDao = ApplicantRequestDaoImpl.getInstance();
    private static final InterviewResultDao interviewResultDao = InterviewResultDaoImpl.getInstance();
    private static final EntityFactory<ApplicantRequest> applicantRequestFactory = ApplicantRequestFactory.getInstance();
    private static final EntityFactory<InterviewResult> interviewResultFactory = InterviewResultFactory.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static final VacancyService vacancyService = VacancyServiceImpl.getInstance();
    private static volatile ApplicantRequestService instance;

    /**
     * Constructs an ApplicantRequestServiceImpl object.
     */
    private ApplicantRequestServiceImpl() {
    }

    /**
     * Returns an ApplicantRequestService object.
     */
    public static ApplicantRequestService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new ApplicantRequestServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean createApplicantRequest(Map<String, String> fields, User applicant) throws ServiceException {
        boolean result = false;
        Optional<ApplicantRequest> requestOptional = applicantRequestFactory.create(fields);
        try {
            if (requestOptional.isPresent()) {
                long vacancyId = Long.parseLong(fields.get(RequestParameter.VACANCY_ID));
                Optional<Vacancy> vacancyOptional = vacancyService.findVacancyById(vacancyId);
                if (vacancyOptional.isPresent()) {
                    ApplicantRequest request = requestOptional.get();
                    request.setApplicant(applicant);
                    request.setVacancy(vacancyOptional.get());
                    if (!applicantRequestExists(request)) {
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
            applicantRequests = applicantRequestDao.findApplicantRequestsById(vacancyId, 0);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return applicantRequests;
    }

    @Override
    public List<ApplicantRequest> findApplicantRequestsByApplicantId(long applicantId) throws ServiceException {
        List<ApplicantRequest> applicantRequests;
        try {
            applicantRequests = applicantRequestDao.findApplicantRequestsById(0, applicantId);
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
                ApplicantState currentState = applicantRequest.getApplicantState();
                String newApplicantState = fields.get(RequestParameter.APPLICANT_STATE);

                if (currentState == ApplicantState.LEFT_REQUEST && !newApplicantState.equals(ApplicantState.LEFT_REQUEST.name()) ||
                        currentState == ApplicantState.READY_FOR_TECHNICAL_INTERVIEW &&
                                applicantRequest.getTechnicalInterviewDate() != null &&
                                !newApplicantState.equals(ApplicantState.READY_FOR_TECHNICAL_INTERVIEW.name()) &&
                                !newApplicantState.equals(ApplicantState.LEFT_REQUEST.name())) {

                    Optional<InterviewResult> interviewResultOptional = interviewResultFactory.create(fields);

                    if (interviewResultOptional.isPresent()) {
                        InterviewResult interviewResult = interviewResultOptional.get();
                        if (applicantRequest.getBasicInterviewResult() == null) {
                            interviewResult.setType(InterviewType.BASIC);
                        } else {
                            interviewResult.setType(InterviewType.TECHNICAL);
                        }
                        result = interviewResultDao.add(interviewResult, applicantRequest.getId());
                        applicantRequestDao.updateApplicantState(applicantRequest.getId(), ApplicantState.valueOf(newApplicantState.toUpperCase(Locale.ROOT)));
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean scheduleTechnicalInterview(String technicalInterviewDateStr, long vacancyId, long applicantId) throws ServiceException {
        boolean result = false;
        Optional<ApplicantRequest> applicantRequestOptional;
        try {
            applicantRequestOptional = applicantRequestDao.findApplicantRequestByVacancyIdAndApplicantId(vacancyId, applicantId);
            if (applicantRequestOptional.isPresent()) {
                ApplicantRequest applicantRequest = applicantRequestOptional.get();
                ApplicantState currentState = applicantRequest.getApplicantState();
                if (currentState == ApplicantState.READY_FOR_TECHNICAL_INTERVIEW &&
                        applicantRequest.getTechnicalInterviewDate() == null) {
                    if (ApplicantRequestValidator.isTechnicalInterviewDateValid(technicalInterviewDateStr)) {
                        LocalDate technicalInterviewDate = LocalDate.parse(technicalInterviewDateStr);
                        applicantRequest.setTechnicalInterviewDate(technicalInterviewDate);
                        result = applicantRequestDao.updateTechnicalInterviewDate(applicantRequest.getId(), technicalInterviewDate);
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    private boolean applicantRequestExists(ApplicantRequest request) throws ServiceException {
        boolean result;
        try {
            result = applicantRequestDao.applicantRequestExists(request);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
