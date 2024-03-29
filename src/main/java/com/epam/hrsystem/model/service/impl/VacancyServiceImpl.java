package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.dao.impl.UserDaoImpl;
import com.epam.hrsystem.model.dao.impl.VacancyDaoImpl;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.dao.VacancyDao;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.factory.impl.VacancyFactory;
import com.epam.hrsystem.model.service.VacancyService;
import com.epam.hrsystem.validator.VacancyValidator;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * VacancyService implementation.
 *
 * @author Aleksey Klevitov
 */
public class VacancyServiceImpl implements VacancyService {
    private static final VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final String PERCENT_SIGN = "%";
    private static final EntityFactory<Vacancy> vacancyFactory = VacancyFactory.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile VacancyService instance;

    /**
     * Constructs an VacancyServiceImpl object.
     */
    private VacancyServiceImpl() {
    }

    /**
     * Returns a VacancyService object.
     */
    public static VacancyService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new VacancyServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean createVacancy(Map<String, String> fields, long employeeId) throws ServiceException {
        try {
            Optional<Vacancy> vacancyOptional = vacancyFactory.create(fields);
            if (vacancyOptional.isPresent()) {
                Optional<User> employee = UserDaoImpl.getInstance().findUserById(employeeId);
                if (employee.isPresent()) {
                    Vacancy vacancy = vacancyOptional.get();
                    vacancy.setEmployee(employee.get());
                    return (addCountryIfNotExists(vacancy.getCountry()) && addCityIfNotExists(vacancy.getCity(), vacancy.getCountry())
                            && (!vacancyDao.vacancyExists(vacancy)) && vacancyDao.add(vacancy));
                }
            }
        } catch (DaoException | NumberFormatException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean deleteVacancy(long vacancyId, long employeeId) throws ServiceException {
        try {
            Optional<Vacancy> vacancy = vacancyDao.findVacancyById(vacancyId);
            return (vacancy.isPresent() && vacancy.get().getEmployee().getId() == employeeId &&
                    vacancyDao.updateVacancyAvailability(vacancyId, (byte) 0));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean restoreVacancy(long vacancyId, long employeeId) throws ServiceException {
        try {
            Optional<Vacancy> vacancy = vacancyDao.findVacancyById(vacancyId);
            return (vacancy.isPresent() && vacancy.get().getEmployee().getId() == employeeId &&
                    vacancyDao.updateVacancyAvailability(vacancyId, (byte) 1));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findAvailableVacancies() throws ServiceException {
        try {
            List<Vacancy> vacancies = vacancyDao.findVacanciesByAvailability(true);
            for (Vacancy vacancy : vacancies) {
                updateVacancyEmployee(vacancy);
            }
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacancies(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = vacancyDao.findEmployeeVacancies(employeeId);
            for (Vacancy vacancy : vacancies) {
                updateVacancyEmployee(vacancy);
            }
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findActiveEmployeeVacancies(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = vacancyDao.findEmployeeVacanciesByAvailability(employeeId, true);
            for (Vacancy vacancy : vacancies) {
                updateVacancyEmployee(vacancy);
            }
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findDeletedEmployeeVacancies(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = vacancyDao.findEmployeeVacanciesByAvailability(employeeId, false);
            for (Vacancy vacancy : vacancies) {
                updateVacancyEmployee(vacancy);
            }
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithApplicantsRequests(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = vacancyDao.findEmployeeVacanciesWithApplicantsRequests(employeeId);
            for (Vacancy vacancy : vacancies) {
                updateVacancyEmployee(vacancy);
            }
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithActiveApplicantsRequests(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = vacancyDao.findEmployeeVacanciesWithApplicantsRequestsByActivity(employeeId, true);
            for (Vacancy vacancy : vacancies) {
                updateVacancyEmployee(vacancy);
            }
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithNotActiveApplicantsRequests(long employeeId) throws ServiceException {
        try {
            List<Vacancy> vacancies = vacancyDao.findEmployeeVacanciesWithApplicantsRequestsByActivity(employeeId, false);
            for (Vacancy vacancy : vacancies) {
                updateVacancyEmployee(vacancy);
            }
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateVacancyInfo(long vacancyId, long employeeId, Map<String, String> fields) throws ServiceException {
        try {
            if (VacancyValidator.isVacancyFormValid(fields)) {
                Optional<Vacancy> vacancyOptional = vacancyDao.findVacancyById(vacancyId);
                if (vacancyOptional.isPresent() && vacancyOptional.get().getEmployee().getId() == employeeId) {
                    Vacancy vacancy = vacancyOptional.get();
                    updateVacancyInfo(vacancy, fields);
                    return (addCountryIfNotExists(vacancy.getCountry()) && addCityIfNotExists(vacancy.getCity(), vacancy.getCountry()) &&
                            !vacancyExists(vacancy) && vacancyDao.updateVacancyInfo(vacancy));
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public Optional<Vacancy> findVacancyById(long vacancyId) throws ServiceException {
        try {
            Optional<Vacancy> vacancyOptional = vacancyDao.findVacancyById(vacancyId);
            if (vacancyOptional.isPresent()) {
                Vacancy vacancy = vacancyOptional.get();
                if (updateVacancyEmployee(vacancy)) {
                    vacancyOptional = Optional.of(vacancy);
                }
            }
            return vacancyOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findVacanciesByKeyWord(String keyWord) throws ServiceException {
        try {
            String keyWordForQuery = PERCENT_SIGN + keyWord + PERCENT_SIGN;
            List<Vacancy> vacancies = vacancyDao.findVacanciesByKeyWord(keyWordForQuery);
            for (Vacancy vacancy : vacancies) {
                updateVacancyEmployee(vacancy);
            }
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean vacancyExists(Vacancy vacancy) throws ServiceException {
        try {
            return vacancyDao.vacancyExists(vacancy);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean addCountryIfNotExists(String name) throws DaoException {
        return (vacancyDao.findCountryIdByName(name.toUpperCase(Locale.ROOT)).isPresent() ||
                vacancyDao.addCounty(name.toUpperCase(Locale.ROOT)));
    }

    private boolean addCityIfNotExists(String city, String country) throws DaoException {
        return (vacancyDao.findCityIdByNameAndCountry(city.toUpperCase(Locale.ROOT), country.toUpperCase(Locale.ROOT)).isPresent() ||
                vacancyDao.addCity(city.toUpperCase(Locale.ROOT), country.toUpperCase(Locale.ROOT)));
    }

    private void updateVacancyInfo(Vacancy vacancy, Map<String, String> fields) {
        String newPosition = fields.get(RequestParameter.POSITION);
        vacancy.setPosition(newPosition);

        String newDescription = fields.get(RequestParameter.DESCRIPTION);
        vacancy.setDescription(newDescription);

        String newCountry = fields.get(RequestParameter.COUNTRY);
        vacancy.setCountry(newCountry);

        String newCity = fields.get(RequestParameter.CITY);
        vacancy.setCity(newCity);
    }

    private boolean updateVacancyEmployee(Vacancy vacancy) throws DaoException {
        boolean result = false;
        Optional<User> employeeOptional = userDao.findUserById(vacancy.getEmployee().getId());
        if (employeeOptional.isPresent()) {
            vacancy.setEmployee(employeeOptional.get());
            result = true;
        }
        return result;
    }
}
