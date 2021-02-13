package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.validator.VacancyValidator;

import java.util.List;
import java.util.Optional;

public interface VacancyDao {
    boolean add(Vacancy vacancy) throws DaoException;

    boolean updateVacancyAvailability(long vacancyId, byte availabilityValue) throws DaoException;

    List<Vacancy> findAllVacancies() throws DaoException;

    List<Vacancy> findDeletedVacancies() throws DaoException;

    List<Vacancy> findAvailableVacancies() throws DaoException;

    boolean updateVacancyInfo(Vacancy vacancy) throws DaoException;

    Optional<Vacancy> findVacancyById(long vacancyId) throws DaoException;

    Optional<Long> findCountryIdByName(String name) throws DaoException;

    Optional<Long> findCityIdByName(String name) throws DaoException;

    boolean addCounty(String name) throws DaoException;

    boolean addCity(String name) throws DaoException;

    boolean vacancyExists(Vacancy vacancy) throws DaoException;
}
