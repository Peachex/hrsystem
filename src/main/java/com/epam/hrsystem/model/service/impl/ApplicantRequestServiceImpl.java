package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.dao.VacancyDao;
import com.epam.hrsystem.model.dao.impl.ApplicantRequestDaoImpl;
import com.epam.hrsystem.model.dao.impl.UserDaoImpl;
import com.epam.hrsystem.model.dao.impl.VacancyDaoImpl;
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
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
    private static final EntityFactory<ApplicantRequest> applicantRequestFactory = ApplicantRequestFactory.getInstance();
    private static final EntityFactory<InterviewResult> interviewResultFactory = InterviewResultFactory.getInstance();
    private static final VacancyService vacancyService = VacancyServiceImpl.getInstance();
    private static final Lock locker = new ReentrantLock();
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
        Optional<ApplicantRequest> requestOptional = applicantRequestFactory.create(fields);
        try {
            if (requestOptional.isPresent()) {
                long vacancyId = Long.parseLong(fields.get(RequestParameter.VACANCY_ID));
                Optional<Vacancy> vacancyOptional = vacancyService.findVacancyById(vacancyId);
                if (vacancyOptional.isPresent()) {
                    ApplicantRequest request = requestOptional.get();
                    request.setApplicant(applicant);
                    request.setVacancy(vacancyOptional.get());
                    return (!applicantRequestDao.applicantRequestExists(request) && applicantRequestDao.add(request));
                }
            }
        } catch (DaoException | NumberFormatException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public List<ApplicantRequest> findApplicantRequestsByVacancyId(long vacancyId) throws ServiceException {
        try {
            List<ApplicantRequest> requests = applicantRequestDao.findApplicantRequestsById(vacancyId, 0);
            for (ApplicantRequest request : requests) {
                updateApplicantRequest(request);
            }
            return requests;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ApplicantRequest> findApplicantRequestsByApplicantId(long applicantId) throws ServiceException {
        try {
            List<ApplicantRequest> requests = applicantRequestDao.findApplicantRequestsById(0, applicantId);
            for (ApplicantRequest request : requests) {
                updateApplicantRequest(request);
            }
            return requests;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<ApplicantRequest> findApplicantRequestByVacancyIdAndApplicantId(long vacancyId, long applicantId) throws ServiceException {
        try {
            Optional<ApplicantRequest> applicantRequestOptional = applicantRequestDao.findApplicantRequestByVacancyIdAndApplicantId(vacancyId, applicantId);
            if (applicantRequestOptional.isPresent()) {
                ApplicantRequest applicantRequest = applicantRequestOptional.get();
                if (updateApplicantRequest(applicantRequest)) {
                    applicantRequestOptional = Optional.of(applicantRequest);
                }
            }
            return applicantRequestOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createInterviewResult(Map<String, String> fields, long vacancyId, long applicantId) throws ServiceException {
        Optional<ApplicantRequest> applicantRequestOptional;
        try {
            applicantRequestOptional = applicantRequestDao.findApplicantRequestByVacancyIdAndApplicantId(vacancyId, applicantId);
            if (applicantRequestOptional.isPresent()) {
                ApplicantRequest applicantRequest = applicantRequestOptional.get();
                String newApplicantState = fields.get(RequestParameter.APPLICANT_STATE);
                if (checkApplicantStates(applicantRequest, newApplicantState)) {
                    Optional<InterviewResult> interviewResultOptional = interviewResultFactory.create(fields);
                    if (interviewResultOptional.isPresent()) {
                        InterviewResult interviewResult = interviewResultOptional.get();
                        interviewResult.setType(applicantRequest.getBasicInterviewResult() == null ? InterviewType.BASIC : InterviewType.TECHNICAL);
                        return (applicantRequestDao.addInterviewResult(interviewResult, applicantRequest.getId()) &&
                                applicantRequestDao.updateApplicantState(applicantRequest.getId(), ApplicantState.valueOf(newApplicantState.toUpperCase(Locale.ROOT))));
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean scheduleTechnicalInterview(String technicalInterviewDateStr, long vacancyId, long applicantId) throws ServiceException {
        try {
            Optional<ApplicantRequest> applicantRequestOptional = applicantRequestDao.findApplicantRequestByVacancyIdAndApplicantId(vacancyId, applicantId);
            if (applicantRequestOptional.isPresent()) {
                ApplicantRequest applicantRequest = applicantRequestOptional.get();
                ApplicantState currentState = applicantRequest.getApplicantState();
                if (currentState == ApplicantState.READY_FOR_TECHNICAL_INTERVIEW && applicantRequest.getTechnicalInterviewDate() == null) {
                    if (ApplicantRequestValidator.isTechnicalInterviewDateValid(technicalInterviewDateStr)) {
                        LocalDate technicalInterviewDate = LocalDate.parse(technicalInterviewDateStr);
                        applicantRequest.setTechnicalInterviewDate(technicalInterviewDate);
                        return applicantRequestDao.updateTechnicalInterviewDate(applicantRequest.getId(), technicalInterviewDate);
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    private boolean checkApplicantStates(ApplicantRequest applicantRequest, String newApplicantState) {
        ApplicantState currentState = applicantRequest.getApplicantState();
        LocalDate technicalInterviewDate = applicantRequest.getTechnicalInterviewDate();
        return (currentState == ApplicantState.LEFT_REQUEST && !newApplicantState.equals(ApplicantState.LEFT_REQUEST.name()) ||
                currentState == ApplicantState.READY_FOR_TECHNICAL_INTERVIEW && technicalInterviewDate != null &&
                        !newApplicantState.equals(ApplicantState.READY_FOR_TECHNICAL_INTERVIEW.name()) &&
                        !newApplicantState.equals(ApplicantState.LEFT_REQUEST.name()));
    }

    private boolean updateApplicantRequest(ApplicantRequest request) throws DaoException {
        boolean result = false;
        Optional<User> applicantOptional = userDao.findUserById(request.getApplicant().getId());
        Optional<Vacancy> vacancyOptional = vacancyDao.findVacancyById(request.getVacancy().getId());
        if (applicantOptional.isPresent() && vacancyOptional.isPresent()) {
            request.setApplicant(applicantOptional.get());
            request.setVacancy(vacancyOptional.get());
            result = true;
        }
        return result;
    }
}
