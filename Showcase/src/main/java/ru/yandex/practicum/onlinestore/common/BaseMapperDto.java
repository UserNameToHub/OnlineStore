package ru.yandex.practicum.onlinestore.common;

/**
 *
 * @param <T1> - entity
 * @param <T2> - dto
 */
public interface BaseMapperDto<T1, T2> {
    default T2 toDto(T1 entity) {
        return null;
    }

    default T1 toEntity(T2 dto) {
        return null;
    }
}
