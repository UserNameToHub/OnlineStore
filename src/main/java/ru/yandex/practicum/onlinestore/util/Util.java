package ru.yandex.practicum.onlinestore.util;

import org.springframework.data.domain.Sort;
import ru.yandex.practicum.onlinestore.enumiration.ActionConstant;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;

import static ru.yandex.practicum.onlinestore.util.Constants.SORT_BY_ALPHA;
import static ru.yandex.practicum.onlinestore.util.Constants.SORT_BY_PRICE;

public class Util {
    public static Sort getSort(SortConstant sort) {
        return switch (sort) {
            case ALPHA -> SORT_BY_ALPHA;
            case PRICE -> SORT_BY_PRICE;
            default -> null;
        };
    }

    public static ActionConstant getAction(String action) {
        return ActionConstant.valueOf(action.toUpperCase());
    }
}
