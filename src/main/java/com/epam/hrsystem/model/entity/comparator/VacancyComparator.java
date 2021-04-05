package com.epam.hrsystem.model.entity.comparator;

import com.epam.hrsystem.model.entity.Vacancy;

import java.util.Comparator;

/**
 * Enumeration of vacancies' comparators.
 *
 * @author Aleksey Klevitov
 */
public enum VacancyComparator implements Comparator<Vacancy> {
    /**
     * Represents an object of enumeration that we can use as comparator to compare vacancies by creation date.
     */
    VACANCY_CREATION_DATE {
        @Override
        public int compare(Vacancy vacancy1, Vacancy vacancy2) {
            return vacancy1.getCreationDate().compareTo(vacancy2.getCreationDate());
        }
    }
}
