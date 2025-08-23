package ru.yandex.practicum.onlinestore.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.onlinestore.dto.Paging;

@Component
public class PagingMapper {
    public Paging toPagingDto(int pageNumber, int pageSize, int totalSize) {
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(start + pageSize, totalSize);
        boolean hasNext = end < totalSize;
        boolean hasPrevious = start > 0;

        return Paging.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .hasNext(hasNext)
                .hasPrevious(hasPrevious)
                .build();
    }
}