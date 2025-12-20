package ru.yandex.practicum.onlinestore.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class StoreClient {
    private final RestTemplate restTemplate;

    public Boolean execute(BigDecimal balance, Long userId) {

        var uri = "/pay/{id}?amount";
        var responseType = Boolean.class;
        var method = HttpMethod.GET;

        var parameters = new HashMap<String, Object>();
        parameters.put("id", userId);
        parameters.put("amount", balance);

        return restTemplate.exchange(
                uri,
                method, null,
                responseType,
                parameters)
                .hasBody();
    }
}