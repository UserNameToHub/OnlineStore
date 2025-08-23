package ru.yandex.practicum.onlinestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yandex.practicum.onlinestore.mapper.PagingMapper;

@Configuration
public class AppConfig {
    @Bean
    PagingMapper pagingMapper() {
        return new PagingMapper();
    }
}
