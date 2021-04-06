package com.epam.hrsystem.model.service;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.Vacancy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Vacancy service.
 *
 * @author Aleksey Klevtiov
 */
public interface VacancyService {
    /**
     * Creates vacancy.
     *
     * @param fields     Map object with vacancy's fields with RequestParameter's constants as keys inside.
     * @param employeeId long value of employee's id.
     * @return boolean value. True if the vacancy has been created, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean createVacancy(Map<String, String> fields, long employeeId) throws ServiceException;

    /**
     * Deletes vacancy.
     *
     * @param vacancyId  long value of vacancy's id.
     * @param employeeId long value of employee's id.
     * @return boolean value. True if the vacancy has been deleted, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean deleteVacancy(long vacancyId, long employeeId) throws ServiceException;

    /**
     * Restores vacancy.
     *
     * @param vacancyId  long value of vacancy's id.
     * @param employeeId long value of employee's id.
     * @return boolean value. True if the vacancy has been restored, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean restoreVacancy(long vacancyId, long employeeId) throws ServiceException;

    /**
     * Finds available vacancies.
     *
     * @return List object of available vacancies.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Vacancy> findAvailableVacancies() throws ServiceException;

    /**
     * Finds employee's vacancies.
     *
     * @param employeeId long value of employee's id.
     * @return List object of employee's vacancies.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Vacancy> findEmployeeVacancies(long employeeId) throws ServiceException;

    /**
     * Finds active employee's vacancies.
     *
     * @param employeeId long value of employee's id.
     * @return List object of active employee's vacancies.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Vacancy> findActiveEmployeeVacancies(long employeeId) throws ServiceException;

    /**
     * Finds deleted employee's vacancies.
     *
     * @param employeeId long value of employee's id.
     * @return List object of deleted employee's vacancies.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Vacancy> findDeletedEmployeeVacancies(long employeeId) throws ServiceException;

    /**
     * Finds employee's vacancies with applicants requests.
     *
     * @param employeeId long value of employee's id.
     * @return List object of employee's vacancies with applicants requests.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Vacancy> findEmployeeVacanciesWithApplicantsRequests(long employeeId) throws ServiceException;

    /**
     * Finds employee's vacancies with active applicants requests.
     *
     * @param employeeId long value of employee's id.
     * @return List object of employee's vacancies with active applicants requests.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Vacancy> findEmployeeVacanciesWithActiveApplicantsRequests(long employeeId) throws ServiceException;

    /**
     * Finds employee's vacancies with not active applicants requests.
     *
     * @param employeeId long value of employee's id.
     * @return List object of employee's vacancies with not active applicants requests.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Vacancy> findEmployeeVacanciesWithNotActiveApplicantsRequests(long employeeId) throws ServiceException;

    /**
     * Updates vacancy's info.
     *
     * @param vacancyId  long value of vacancy's id.
     * @param employeeId long value of employee's id.
     * @param fields     Map object with vacancy's fields with RequestParameter's constants as keys inside.
     * @return boolean value. True if the vacancy's info has been updated, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean updateVacancyInfo(long vacancyId, long employeeId, Map<String, String> fields) throws ServiceException;

    /**
     * Finds vacancy by id.
     *
     * @param vacancyId long value of vacancy's id.
     * @return Optional object of vacancy if exists, Optional.empty() otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<Vacancy> findVacancyById(long vacancyId) throws ServiceException;

    /**
     * Finds vacancies by key word.
     *
     * @param keyWord String object. Key word used to find vacancies.
     * @return List object of vacancies.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Vacancy> findVacanciesByKeyWord(String keyWord) throws ServiceException;
}
