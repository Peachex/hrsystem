package com.epam.hrsystem.model.factory.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.entity.Vacancy;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.hrsystem.validator.VacancyValidator.isVacancyFormValid;

/**
 * EntityFactory implementation used to create a Vacancy object.
 *
 * @author Aleksey Klevitov
 */
public class VacancyFactory implements EntityFactory<Vacancy> {
    private static final boolean DEFAULT_AVAILABILITY_VALUE = true;
    private static final Lock locker = new ReentrantLock();
    private static EntityFactory<Vacancy> instance;

    /**
     * Constructs a VacancyFactory object.
     */
    private VacancyFactory() {
    }

    /**
     * Returns an EntityFactory object.
     */
    public static EntityFactory<Vacancy> getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new VacancyFactory();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public Optional<Vacancy> create(Map<String, String> fields) {
        Optional<Vacancy> result = Optional.empty();
        if (isVacancyFormValid(fields)) {
            String position = fields.get(RequestParameter.POSITION);
            String description = fields.get(RequestParameter.DESCRIPTION);
            String country = fields.get(RequestParameter.COUNTRY);
            String city = fields.get(RequestParameter.CITY);
            LocalDate creationDate = LocalDate.now();
            result = Optional.of(new Vacancy(DEFAULT_AVAILABILITY_VALUE, position, description, country, city, creationDate));
        }
        return result;
    }
}
