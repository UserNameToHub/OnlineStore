package ru.yandex.practicum.onlinestore.util;

import org.springframework.data.domain.Sort;

public class Constants {
    public static final String UPLOAD_DIR = "C:/store/uploads/";

    public static final Sort SORT_BY_PRICE = Sort.by(Sort.Direction.ASC, "price");

    public static final Sort SORT_BY_ALPHA = Sort.by(Sort.Direction.ASC, "title");
}