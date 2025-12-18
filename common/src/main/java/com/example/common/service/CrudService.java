package com.example.common.service;

import java.util.List;

/**
 *
 * @param <T> - type
 * @param <ID> - id
 *
 * There is all default methods so we can use one
 */
public interface CrudService<T, ID> {
    default ID create(T obj) {
        return null;
    }

    default T getById(ID id) {
        return null;
    };

    default List<T> getAll() {
        return null;
    }

    default T update(T obj) {
        return null;
    }

    default void delete(ID id) {

    }
}
