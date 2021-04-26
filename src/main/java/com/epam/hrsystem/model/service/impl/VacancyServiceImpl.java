package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
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
    private static final VacancyDao dao = VacancyDaoImpl.getInstance();
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
                            && (!dao.vacancyExists(vacancy)) && dao.add(vacancy));
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
            Optional<Vacancy> vacancy = dao.findVacancyById(vacancyId);
            return (vacancy.isPresent() && vacancy.get().getEmployee().getId() == employeeId &&
                    dao.updateVacancyAvailability(vacancyId, (byte) 0));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean restoreVacancy(long vacancyId, long employeeId) throws ServiceException {
        try {
            Optional<Vacancy> vacancy = dao.findVacancyById(vacancyId);
            return (vacancy.isPresent() && vacancy.get().getEmployee().getId() == employeeId &&
                    dao.updateVacancyAvailability(vacancyId, (byte) 1));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findAvailableVacancies() throws ServiceException {
        try {
            return dao.findVacanciesByAvailability(true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacancies(long employeeId) throws ServiceException {
        try {
            return dao.findEmployeeVacancies(employeeId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findActiveEmployeeVacancies(long employeeId) throws ServiceException {
        try {
            return dao.findEmployeeVacanciesByAvailability(employeeId, true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findDeletedEmployeeVacancies(long employeeId) throws ServiceException {
        try {
            return dao.findEmployeeVacanciesByAvailability(employeeId, false);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithApplicantsRequests(long employeeId) throws ServiceException {
        try {
            return dao.findEmployeeVacanciesWithApplicantsRequests(employeeId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithActiveApplicantsRequests(long employeeId) throws ServiceException {
        try {
            return dao.findEmployeeVacanciesWithApplicantsRequestsByActivity(employeeId, true);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithNotActiveApplicantsRequests(long employeeId) throws ServiceException {
        try {
            return dao.findEmployeeVacanciesWithApplicantsRequestsByActivity(employeeId, false);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateVacancyInfo(long vacancyId, long employeeId, Map<String, String> fields) throws ServiceException {
        try {
            if (VacancyValidator.isVacancyFormValid(fields)) {
                Optional<Vacancy> vacancyOptional = dao.findVacancyById(vacancyId);
                if (vacancyOptional.isPresent() && vacancyOptional.get().getEmployee().getId() == employeeId) {
                    Vacancy vacancy = vacancyOptional.get();
                    updateVacancyInfo(vacancy, fields);
                    return (addCountryIfNotExists(vacancy.getCountry()) && addCityIfNotExists(vacancy.getCity(), vacancy.getCountry()) &&
                            !vacancyExists(vacancy) && dao.updateVacancyInfo(vacancy));
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
            return dao.findVacancyById(vacancyId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findVacanciesByKeyWord(String keyWord) throws ServiceException {
        try {
            String keyWordForQuery = PERCENT_SIGN + keyWord + PERCENT_SIGN;
            return dao.findVacanciesByKeyWord(keyWordForQuery);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean vacancyExists(Vacancy vacancy) throws ServiceException {
        try {
            return dao.vacancyExists(vacancy);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean addCountryIfNotExists(String name) throws DaoException {
        return (dao.findCountryIdByName(name.toUpperCase(Locale.ROOT)).isPresent() ||
                dao.addCounty(name.toUpperCase(Locale.ROOT)));
    }

    private boolean addCityIfNotExists(String city, String country) throws DaoException {
        return (dao.findCityIdByNameAndCountry(city.toUpperCase(Locale.ROOT), country.toUpperCase(Locale.ROOT)).isPresent() ||
                dao.addCity(city.toUpperCase(Locale.ROOT), country.toUpperCase(Locale.ROOT)));
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
}
