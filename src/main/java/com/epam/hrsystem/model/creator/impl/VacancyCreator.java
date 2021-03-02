package com.epam.hrsystem.model.creator.impl;

import com.epam.hrsystem.controller.attribute.Constant;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.model.creator.Creator;
import com.epam.hrsystem.model.entity.Vacancy;

import java.util.Map;
import java.util.Optional;

import static com.epam.hrsystem.validator.VacancyValidator.isVacancyFormValid;

public enum VacancyCreator implements Creator<Vacancy> {
    INSTANCE;
    private static final boolean DEFAULT_AVAILABILITY_VALUE = true;

    @Override
    public Optional<Vacancy> create(Map<String, String> fields) {
        Optional<Vacancy> result = Optional.empty();
        if (isVacancyFormValid(fields)) {
            String position = fields.get(RequestParameter.POSITION);
            String description = fields.get(RequestParameter.DESCRIPTION).replaceAll(Constant.NEW_LINE_SYMBOL, Constant.NEW_LINE_HTML_TAG);
            String country = fields.get(RequestParameter.COUNTRY);
            String city = fields.get(RequestParameter.CITY);
            Vacancy vacancy = new Vacancy(DEFAULT_AVAILABILITY_VALUE, position, description, country, city);
            result = Optional.of(vacancy);
        }
        return result;
    }
}