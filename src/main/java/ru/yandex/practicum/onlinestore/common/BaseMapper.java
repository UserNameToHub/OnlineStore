package ru.yandex.practicum.onlinestore.common;

import java.util.List;

/**
 *
 * @param <T1> - entity
 * @param <T2> - dto
 */
public interface BaseMapper<T1, T2> {
    T2 toDto(List<T1> entities);
}
