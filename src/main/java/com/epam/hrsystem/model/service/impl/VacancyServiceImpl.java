package com.epam.hrsystem.model.service.impl;

import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.creator.Creator;
import com.epam.hrsystem.model.creator.impl.VacancyCreator;
import com.epam.hrsystem.model.dao.VacancyDao;
import com.epam.hrsystem.model.dao.impl.UserDaoImpl;
import com.epam.hrsystem.model.dao.impl.VacancyDaoImpl;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.service.VacancyService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public enum VacancyServiceImpl implements VacancyService {
    INSTANCE;

    private static final VacancyDao dao = VacancyDaoImpl.INSTANCE;

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
                    if (addCountryIfNotExists(vacancy.getCountry()) && addCityIfNotExists(vacancy.getCity())) {
                        result = dao.add(vacancy);
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean deleteVacancy(long vacancyId) throws ServiceException {
        return false;
    }

    @Override
    public boolean restoreVacancy(long vacancyId) throws ServiceException {
        return false;
    }

    @Override
    public List<Vacancy> findAllVacancies() throws ServiceException {
        try {
            List<Vacancy> vacancies = dao.findAllVacancies();
            return vacancies;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> findDeletedVacancies() throws ServiceException {
        return null;
    }

    @Override
    public List<Vacancy> findAvailableVacancies() throws ServiceException {
        return null;
    }

    @Override
    public boolean updateVacancyInfo(long vacancyId, Map<String, String> newFields) throws ServiceException {
        return false;
    }

    private boolean addCountryIfNotExists(String name) throws DaoException {
        boolean result = true;
        Optional<Long> idOptional = dao.findCountryIdByName(name);
        if (!idOptional.isPresent()) {
            result = dao.addCounty(name);
        }
        return result;
    }

    private boolean addCityIfNotExists(String name) throws DaoException {
        boolean result = true;
        Optional<Long> idOptional = dao.findCityIdByName(name);
        if (!idOptional.isPresent()) {
            result = dao.addCity(name);
        }
        return result;
    }
}
