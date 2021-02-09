package com.epam.hrsystem.model.dao;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.entity.Vacancy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VacancyDao {
    boolean add(Vacancy vacancy) throws DaoException;

    Optional<Byte> findVacancyAvailability(long vacancyId) throws DaoException;

    boolean updateVacancyAvailability(long vacancyId, byte availabilityValue) throws DaoException;

    List<Vacancy> findAllVacancies() throws DaoException;

    List<Vacancy> findDeletedVacancies() throws DaoException;

    List<Vacancy> findAvailableVacancies() throws DaoException;

    boolean updateVacancyInfo(long userId, Map<String, String> fields) throws DaoException;

    Optional<Vacancy> findVacancyById(long userId) throws DaoException;

    Optional<Long> findCountryIdByName(String name) throws DaoException;

    Optional<Long> findCityIdByName(String name) throws DaoException;

    boolean addCounty(String name) throws DaoException;

    boolean addCity(String name) throws DaoException;
}
