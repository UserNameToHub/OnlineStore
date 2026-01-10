package ru.yandex.practicum.onlinestore.confiquration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import ru.yandex.practicum.onlinestore.mapper.UserDetailsMapper;
import ru.yandex.practicum.onlinestore.repository.RedisSessionServerSecurityContextRepository;
import ru.yandex.practicum.onlinestore.repository.UserRepository;
import ru.yandex.practicum.onlinestore.service.Impl.JpaUserDetailService;
import ru.yandex.practicum.onlinestore.service.Impl.ReactiveUserAuthenticationManager;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        ReactiveRedisTemplate<String, Object> template = new ReactiveRedisTemplate<>(connectionFactory, new StringRedisSerializer(), new StringRedisSerializer());
        return template;
    }

    @Bean
    ServerSecurityContextRepository securityContextRepository(ReactiveRedisTemplate reactiveRedisTemplate) {
        return new RedisSessionServerSecurityContextRepository(reactiveRedisTemplate);
    }

    @Bean
    ReactiveUserDetailsService userDetailsService(UserRepository userRepository, UserDetailsMapper mapper) {
        return new JpaUserDetailService(userRepository, mapper);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(3);
    }

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        return http
                .securityContextRepository(securityContextRepository(reactiveRedisTemplate(reactiveRedisConnectionFactory())))
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/admin/**").hasRole("ADMIN")
                        .pathMatchers("/cart/**", "/orders?**", "/users/**").authenticated()
                        .anyExchange().permitAll()
                )
                .build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        return new ReactiveUserAuthenticationManager(userDetailsService, passwordEncoder);
    }
}