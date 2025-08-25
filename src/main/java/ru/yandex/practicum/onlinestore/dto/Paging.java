package ru.yandex.practicum.onlinestore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Paging {
    private Integer pageSize;

    private Integer pageNumber;

    private boolean hasPrevious;

    private boolean hasNext;
}
