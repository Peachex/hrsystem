package com.epam.hrsystem.model.creator.impl;

import com.epam.hrsystem.RequestParameter;
import com.epam.hrsystem.model.creator.Creator;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.UserRole;
import com.epam.hrsystem.model.entity.Vacancy;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static com.epam.hrsystem.validator.VacancyValidator.isPositionValid;
import static com.epam.hrsystem.validator.VacancyValidator.isDescriptionValid;
import static com.epam.hrsystem.validator.VacancyValidator.isCountryValid;
import static com.epam.hrsystem.validator.VacancyValidator.isCityValid;

public class VacancyCreator implements Creator<Vacancy> {
    private static final boolean DEFAULT_AVAILABILITY_VALUE = true;

    @Override
    public Optional<Vacancy> create(Map<String, String> fields) {
        Optional<Vacancy> result = Optional.empty();

        String position = fields.get(RequestParameter.POSITION);
        String description = fields.get(RequestParameter.DESCRIPTION);
        String country = fields.get(RequestParameter.COUNTRY);
        String city = fields.get(RequestParameter.CITY);

        if (isPositionValid(position) && isDescriptionValid(description) && isCountryValid(country) && isCityValid(city)) {
            Vacancy vacancy = new Vacancy(DEFAULT_AVAILABILITY_VALUE, position, description, country, city);
            result = Optional.of(vacancy);
        }
        return result;
    }
}