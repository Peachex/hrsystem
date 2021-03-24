package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.dao.impl.DaoHolder;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.factory.impl.FactoryHolder;
import com.epam.hrsystem.model.dao.VacancyDao;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.validator.VacancyValidator;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class VacancyServiceImpl implements VacancyService {
    private static final VacancyDao dao = DaoHolder.HOLDER.getVacancyDao();
    private static final String PERCENT_SIGN = "%";

    VacancyServiceImpl() {
    }

    @Override
    public boolean createVacancy(Map<String, String> fields, long employeeId) throws ServiceException {
        boolean result = false;
        EntityFactory<Vacancy> factory = FactoryHolder.HOLDER.getVacancyFactory();
        Optional<Vacancy> vacancyOptional = factory.create(fields);
        try {
            if (vacancyOptional.isPresent()) {
                Optional<User> employee = DaoHolder.HOLDER.getUserDao().findUserById(employeeId);
                if (employee.isPresent()) {
                    Vacancy vacancy = vacancyOptional.get();
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
    public boolean deleteVacancy(long vacancyId, long employeeId) throws ServiceException {
        boolean result = false;
        try {
            Optional<Vacancy> vacancy = dao.findVacancyById(vacancyId);
            if (vacancy.isPresent() && vacancy.get().getEmployee().getId() == employeeId) {
                result = dao.updateVacancyAvailability(vacancyId, (byte) 0);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean restoreVacancy(long vacancyId, long employeeId) throws ServiceException {
        boolean result = false;
        try {
            Optional<Vacancy> vacancy = dao.findVacancyById(vacancyId);
            if (vacancy.isPresent() && vacancy.get().getEmployee().getId() == employeeId) {
                result = dao.updateVacancyAvailability(vacancyId, (byte) 1);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Vacancy> findAvailableVacancies() throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findVacanciesByAvailability(true);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacancies(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findEmployeeVacancies(employeeId);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findActiveEmployeeVacancies(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findEmployeeVacanciesByAvailability(employeeId, true);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findDeletedEmployeeVacancies(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findEmployeeVacanciesByAvailability(employeeId, false);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithApplicantsRequests(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findEmployeeVacanciesWithApplicantsRequests(employeeId);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithActiveApplicantsRequests(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findEmployeeVacanciesWithApplicantsRequestsByActivity(employeeId, true);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithNotActiveApplicantsRequests(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findEmployeeVacanciesWithApplicantsRequestsByActivity(employeeId, false);
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateVacancyInfo(long vacancyId, long employeeId, Map<String, String> fields) throws ServiceException {
        boolean result = false;
        try {
            if (VacancyValidator.isVacancyFormValid(fields)) {
                Optional<Vacancy> vacancyOptional = dao.findVacancyById(vacancyId);
                if (vacancyOptional.isPresent() && vacancyOptional.get().getEmployee().getId() == employeeId) {
                    Vacancy vacancy = vacancyOptional.get();
                    updateVacancyInfo(vacancy, fields);
                    if (!vacancyExists(vacancy) && addCountryIfNotExists(vacancy.getCountry()) && addCityIfNotExists(vacancy.getCity())) {
                        result = dao.updateVacancyInfo(vacancy);
                    }
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
