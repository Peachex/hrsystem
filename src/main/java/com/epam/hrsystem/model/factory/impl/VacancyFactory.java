package com.epam.hrsystem.model.factory.impl;

import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.factory.EntityFactory;
import com.epam.hrsystem.model.entity.Vacancy;

import java.util.Map;
import java.util.Optional;

import static com.epam.hrsystem.validator.VacancyValidator.isVacancyFormValid;

public class VacancyFactory implements EntityFactory<Vacancy> {
    private static final boolean DEFAULT_AVAILABILITY_VALUE = true;

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
            Vacancy vacancy = new Vacancy(DEFAULT_AVAILABILITY_VALUE, position, description, country, city);
            result = Optional.of(vacancy);
        }
        return result;
    }
}
