package ru.yandex.practicum.onlinestore.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReactiveUserAuthenticationManager implements ReactiveAuthenticationManager {
    private final ReactiveUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String username = authentication.getName();
        return userDetailsService.findByUsername(username)
            .flatMap(userDetails -> {
                if (passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
                    return Mono.just(authentication);
                } else {
                    return Mono.error(new BadCredentialsException("Invalid username or password"));
                }
            });
    }
}