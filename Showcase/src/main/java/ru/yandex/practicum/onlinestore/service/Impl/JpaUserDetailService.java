package ru.yandex.practicum.onlinestore.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.mapper.UserDetailsMapper;
import ru.yandex.practicum.onlinestore.repository.UserRepository;

@RequiredArgsConstructor
public class JpaUserDetailService implements ReactiveUserDetailsService{
    private final UserRepository userRepository;
    private final UserDetailsMapper mapper;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username).map(mapper::toDto);
    }
}
