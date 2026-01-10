package ru.yandex.practicum.onlinestore.confiquration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.yandex.practicum.onlinestore.client.StoreClient;

@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {
    @Value(value = "${payment.url}")
    private final String url;

    @Bean
    RestTemplate paymentRestTemplate() {
        return new RestTemplateBuilder().rootUri(url).build();
    }

    @Bean
     StoreClient storeClient(RestTemplate restTemplate) {
        return new StoreClient(restTemplate);
    }
}
