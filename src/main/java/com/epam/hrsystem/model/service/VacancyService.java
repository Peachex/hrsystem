package com.epam.hrsystem.model.service;

import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.Vacancy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VacancyService {
    boolean createVacancy(Map<String, String> fields, long employeeId) throws ServiceException;

    boolean deleteVacancy(long vacancyId) throws ServiceException;

    boolean restoreVacancy(long vacancyId) throws ServiceException;

    List<Vacancy> findAllVacancies() throws ServiceException;

    List<Vacancy> findDeletedVacancies() throws ServiceException;

    List<Vacancy> findAvailableVacancies() throws ServiceException;

    List<Vacancy> findEmployeeVacancies(long employeeId) throws ServiceException;

    List<Vacancy> findActiveEmployeeVacancies(long employeeId) throws ServiceException;

    List<Vacancy> findDeletedEmployeeVacancies(long employeeId) throws ServiceException;

    boolean updateVacancyInfo(long vacancyId, Map<String, String> fields) throws ServiceException;

    boolean vacancyExists(Vacancy vacancy) throws ServiceException;

    Optional<Vacancy> findVacancyById(long vacancyId) throws ServiceException;

    List<Vacancy> findVacanciesByKeyWord(String keyWord) throws ServiceException;
}
