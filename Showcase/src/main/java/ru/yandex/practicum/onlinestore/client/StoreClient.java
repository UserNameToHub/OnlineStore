package ru.yandex.practicum.onlinestore.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreClient {
    private final RestTemplate restTemplate;

    private final OAuth2AuthorizedClientManager manager;

    public Boolean execute(BigDecimal balance, Long userId) {
        var client = manager.authorize(OAuth2AuthorizeRequest
                .withClientRegistrationId("showcase")
                .principal("system")
                .build()
        );

        var accessToken = client.getAccessToken().getTokenValue();

        var uri = "/pay/{id}?amount";
        var responseType = Boolean.class;
        var method = HttpMethod.GET;
        var header = new HttpHeaders();
        header.put(HttpHeaders.AUTHORIZATION, List.of("Bearer " + accessToken));
        var requestEntity = new HttpEntity<>(null, header);

        var parameters = new HashMap<String, Object>();
        parameters.put("id", userId);
        parameters.put("amount", balance);

        return restTemplate.exchange(
                uri,
                method, requestEntity,
                responseType,
                parameters)
                .hasBody();
    }
}