package com.epam.hrsystem.model.entity.comparator;

import com.epam.hrsystem.model.entity.Vacancy;

import java.util.Comparator;

public enum VacancyComparator implements Comparator<Vacancy> {
    VACANCY_CREATION_DATE {
        @Override
        public int compare(Vacancy vacancy1, Vacancy vacancy2) {
            return vacancy1.getCreationDate().compareTo(vacancy2.getCreationDate());
        }
    }
}
