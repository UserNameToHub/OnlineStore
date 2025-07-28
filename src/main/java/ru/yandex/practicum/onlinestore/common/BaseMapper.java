package ru.yandex.practicum.onlinestore.common;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @param <T1> - entity
 * @param <T2> - dto
 */
public interface BaseMapper<T1, T2> {
    T1 toEntity(T2 dto);

    T2 toDto(T1 entity);

    default List<T2> toDtoList(List<T1> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
