package ru.yandex.practicum.onlinestore.common;

/**
 *
 * @param <T1> - entity
 * @param <T2> - dto
 */
public interface BaseMapperDto<T1, T2> {
    T2 toDto(T1 entity);
}
