package com.epam.hrsystem.model.factory.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.entity.Vacancy;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static com.epam.hrsystem.validator.VacancyValidator.isVacancyFormValid;

/**
 * EntityFactory implementation used to create a Vacancy object.
 *
 * @author Aleksey Klevitov
 */
public class VacancyFactory implements EntityFactory<Vacancy> {
    private static final boolean DEFAULT_AVAILABILITY_VALUE = true;

    /**
     * Constructs a VacancyFactory object.
     */
    VacancyFactory() {
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
            Vacancy vacancy = new Vacancy(DEFAULT_AVAILABILITY_VALUE, position, description, country, city, creationDate);
            result = Optional.of(vacancy);
        }
        return result;
    }
}
