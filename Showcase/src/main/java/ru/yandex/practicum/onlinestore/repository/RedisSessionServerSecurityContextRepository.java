package ru.yandex.practicum.onlinestore.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisSessionServerSecurityContextRepository implements ServerSecurityContextRepository {
    private final ReactiveRedisTemplate<String, Object> redis;
    private String springSecurityContextAttrName = "SPRING_SECURITY_CONTEXT";
    private String staticKey = "user:";

    private ObjectMapper objectMapper = new ObjectMapper();

    private Duration ttl = Duration.ofMinutes(20);

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        var fullKey = new StringBuilder(staticKey);

        return exchange.getSession().doOnNext((session) -> {
            if (context == null) {
                session.getAttributes().remove(this.springSecurityContextAttrName);
                log.debug(String.format("Removed SecurityContext stored in WebSession: '%s'", session));
            } else {
                session.getAttributes().put(this.springSecurityContextAttrName, context);
                log.debug(String.format("Saved SecurityContext '%s' in WebSession: '%s'", context, session));

                SecurityContext sc = (SecurityContext) session.getAttribute(this.springSecurityContextAttrName);
                redis.opsForValue().set(staticKey, sc.toString(), ttl);
            }
            redis.opsForValue().set(fullKey.toString(), exchange.toString(), ttl);
        }).then();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return exchange.getSession().flatMap((session) -> {
            SecurityContext sc = (SecurityContext) session.getAttribute(this.springSecurityContextAttrName);
            if (sc != null) {
                return redis.opsForValue().get(staticKey + sc.getAuthentication().getName())
                        .map(serializedSc -> objectMapper.readValue(serializedSc, SecurityContext.class))
                        .onErrorResume(exception -> {
                            if (exception instanceof JsonProcessingException || exception instanceof JsonMappingException) {
                                log.error("Error deserializing SecurityContext", exception);
                                return Mono.empty();
                            } else {
                                throw new RuntimeException(exception);
                            }
                        });
            } else {
                log.debug("No SecurityContext found in WebSession");
                return Mono.empty();
            }
        });
    }
}