package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.creator.Creator;
import com.epam.hrsystem.model.creator.impl.VacancyCreator;
import com.epam.hrsystem.model.dao.SqlQuery;
import com.epam.hrsystem.model.dao.VacancyDao;
import com.epam.hrsystem.model.dao.impl.UserDaoImpl;
import com.epam.hrsystem.model.dao.impl.VacancyDaoImpl;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.validator.VacancyValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public enum VacancyServiceImpl implements VacancyService {
    INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private static final VacancyDao dao = VacancyDaoImpl.INSTANCE;
    private static final String PERCENT_SIGN = "%";

    @Override
    public boolean createVacancy(Map<String, String> fields, long employeeId) throws ServiceException {
        boolean result = false;
        Creator<Vacancy> creator = new VacancyCreator();
        Optional<Vacancy> vacancyOptional = creator.create(fields);
        try {
            if (vacancyOptional.isPresent()) {
                Optional<User> employee = UserDaoImpl.INSTANCE.findUserById(employeeId);
                if (employee.isPresent()) {
                    Vacancy vacancy = vacancyOptional.get();
                    vacancy.setCreationDate(LocalDate.now());
                    vacancy.setEmployee(employee.get());
                    if (addCountryIfNotExists(vacancy.getCountry()) && addCityIfNotExists(vacancy.getCity())
                            && (!dao.vacancyExists(vacancy))) {
                        result = dao.add(vacancy);
                    }
                }
            }
        } catch (DaoException | NumberFormatException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean deleteVacancy(long vacancyId) throws ServiceException {
        boolean result;
        try {
            result = dao.updateVacancyAvailability(vacancyId, (byte) 0);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean restoreVacancy(long vacancyId) throws ServiceException {
        boolean result;
        try {
            result = dao.updateVacancyAvailability(vacancyId, (byte) 1);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Vacancy> findAllVacancies() throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findVacanciesBySqlQuery(SqlQuery.SQL_SELECT_ALL_VACANCIES);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findDeletedVacancies() throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findVacanciesBySqlQuery(SqlQuery.SQL_SELECT_DELETED_VACANCIES);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findAvailableVacancies() throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findVacanciesBySqlQuery(SqlQuery.SQL_SELECT_AVAILABLE_VACANCIES);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateVacancyInfo(long vacancyId, Map<String, String> fields) throws ServiceException {
        boolean result = false;
        try {
            Optional<Vacancy> vacancyOptional = dao.findVacancyById(vacancyId);
            if (vacancyOptional.isPresent()) {
                Vacancy vacancy = vacancyOptional.get();
                updateVacancyInfo(vacancy, fields);
                if (addCountryIfNotExists(vacancy.getCountry()) && addCityIfNotExists(vacancy.getCity())) {
                    result = dao.updateVacancyInfo(vacancy);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean vacancyExists(Vacancy vacancy) throws ServiceException {
        boolean result;
        try {
            result = dao.vacancyExists(vacancy);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Optional<Vacancy> findVacancyById(long vacancyId) throws ServiceException {
        Optional<Vacancy> vacancy;
        try {
            vacancy = dao.findVacancyById(vacancyId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return vacancy;
    }

    @Override
    public List<Vacancy> findVacanciesByKeyWord(String keyWord) throws ServiceException {
        try {
            String keyWordForQuery = PERCENT_SIGN + keyWord + PERCENT_SIGN;
            List<Vacancy> vacancies = dao.findVacanciesByKeyWord(keyWordForQuery);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean addCountryIfNotExists(String name) throws DaoException {
        boolean result = true;
        Optional<Long> idOptional = dao.findCountryIdByName(name.toUpperCase(Locale.ROOT));
        if (!idOptional.isPresent()) {
            result = dao.addCounty(name.toUpperCase(Locale.ROOT));
        }
        return result;
    }

    private boolean addCityIfNotExists(String name) throws DaoException {
        boolean result = true;
        Optional<Long> idOptional = dao.findCityIdByName(name.toUpperCase(Locale.ROOT));
        if (!idOptional.isPresent()) {
            result = dao.addCity(name.toUpperCase(Locale.ROOT));
        }
        return result;
    }

    private void updateVacancyInfo(Vacancy vacancy, Map<String, String> fields) {
        String newPosition = fields.get(RequestParameter.POSITION);
        if (VacancyValidator.isPositionValid(newPosition)) {
            vacancy.setPosition(newPosition);
        }
        String newDescription = fields.get(RequestParameter.DESCRIPTION);
        if (VacancyValidator.isDescriptionValid(newDescription)) {
            vacancy.setDescription(newDescription);
        }
        String newCountry = fields.get(RequestParameter.COUNTRY);
        if (VacancyValidator.isCountryValid(newCountry)) {
            vacancy.setCountry(newCountry);
        }
        String newCity = fields.get(RequestParameter.CITY);
        if (VacancyValidator.isCityValid(newCity)) {
            vacancy.setCity(newCity);
        }
    }
}
