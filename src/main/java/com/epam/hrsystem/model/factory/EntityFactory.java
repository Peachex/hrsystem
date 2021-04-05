package com.epam.hrsystem.model.factory;

import java.util.Map;
import java.util.Optional;

/**
 * Interface used for creating objects with its validation.
 *
 * @param <T> the type of class, which object should be created.
 * @author Aleksey Klevitov
 */
public interface EntityFactory<T> {
    /**
     * Creates an Optional object of the given type from the Map of its fields.
     *
     * @param fields Map object with object's fields with RequestParameter's keys.
     * @return Not empty Optional if the object was created, Optional.empty() otherwise.
     */
    Optional<T> create(Map<String, String> fields);
}
