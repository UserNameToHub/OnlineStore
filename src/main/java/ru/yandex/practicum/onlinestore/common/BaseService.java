package ru.yandex.practicum.onlinestore.common;

/**
 *
 * @param <ID> - id
 */
public interface BaseService<ID> {
    boolean existsById(ID id);
}
