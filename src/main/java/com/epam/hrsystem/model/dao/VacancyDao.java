package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.Vacancy;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with vacancies table.
 *
 * @author Aleksey Klevitov
 */
public interface VacancyDao {
    /**
     * Adds vacancy to the table.
     *
     * @param vacancy Vacancy object.
     * @return boolean value. True if the vacancy has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean add(Vacancy vacancy) throws DaoException;

    /**
     * Updates vacancy's availability.
     *
     * @param vacancyId         long value of vacancy's id.
     * @param availabilityValue boolean value of vacancy's availability.
     * @return boolean value. True if the vacancy's availability has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateVacancyAvailability(long vacancyId, byte availabilityValue) throws DaoException;

    /**
     * Finds vacancies by availability.
     *
     * @param areAvailable boolean value of vacancies' availability.
     * @return List object of vacancies.
     * @throws DaoException if the database throws SQLException.
     */
    List<Vacancy> findVacanciesByAvailability(boolean areAvailable) throws DaoException;

    /**
     * Finds employee's vacancies.
     *
     * @param employeeId long value of employee's id.
     * @return List object of vacancies.
     * @throws DaoException if the database throws SQLException.
     */
    List<Vacancy> findEmployeeVacancies(long employeeId) throws DaoException;

    /**
     * Finds employee's vacancies by availability.
     *
     * @param employeeId   long value of employee's id.
     * @param areAvailable boolean value of vacancies' availability.
     * @return List object of vacancies.
     * @throws DaoException if the database throws SQLException.
     */
    List<Vacancy> findEmployeeVacanciesByAvailability(long employeeId, boolean areAvailable) throws DaoException;

    /**
     * Finds employee's vacancies with applicants requests.
     *
     * @param employeeId long value of employee's id.
     * @return List object of vacancies.
     * @throws DaoException if the database throws SQLException.
     */
    List<Vacancy> findEmployeeVacanciesWithApplicantsRequests(long employeeId) throws DaoException;

    /**
     * Finds employee's vacancies with applicants requests by activity.
     *
     * @param employeeId long value of employee's id.
     * @param areActive  boolean value of applicant requests' availability.
     * @return List object of vacancies.
     * @throws DaoException if the database throws SQLException.
     */
    List<Vacancy> findEmployeeVacanciesWithApplicantsRequestsByActivity(long employeeId, boolean areActive) throws DaoException;

    /**
     * Finds vacancies by key word.
     *
     * @param keyWord String object. Key word used to find vacancies.
     * @return List object of vacancies.
     * @throws DaoException if the database throws SQLException.
     */
    List<Vacancy> findVacanciesByKeyWord(String keyWord) throws DaoException;

    /**
     * Updates vacancy's info.
     *
     * @param vacancy Vacancy object.
     * @return boolean value. True if the vacancy's info has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateVacancyInfo(Vacancy vacancy) throws DaoException;

    /**
     * Finds vacancy by id.
     *
     * @param vacancyId long value of vacancy's id.
     * @return Optional object of vacancy if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<Vacancy> findVacancyById(long vacancyId) throws DaoException;

    /**
     * Finds country's id by name.
     *
     * @param name String object of country's name.
     * @return Optional object of long value if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<Long> findCountryIdByName(String name) throws DaoException;

    /**
     * Finds city's id by name.
     *
     * @param name String object of city's name.
     * @return Optional object of long value if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<Long> findCityIdByName(String name) throws DaoException;

    /**
     * Adds county to the table.
     *
     * @param name String object of country's name.
     * @return boolean value. True if the country has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean addCounty(String name) throws DaoException;

    /**
     * Adds city to the table.
     *
     * @param city    String object of city's name.
     * @param country String object of country's name.
     * @return boolean value. True if the city has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean addCity(String city, String country) throws DaoException;

    /**
     * Check if vacancy exists.
     *
     * @param vacancy Vacancy object.
     * @return boolean value. True if the vacancy exists, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean vacancyExists(Vacancy vacancy) throws DaoException;
}
