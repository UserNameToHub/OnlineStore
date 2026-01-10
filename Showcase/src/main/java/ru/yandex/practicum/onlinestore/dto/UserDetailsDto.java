package ru.yandex.practicum.onlinestore.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Data
@Builder
public class UserDetailsDto implements UserDetails {
    private String username;

    private String password;

    private Flux<? extends GrantedAuthority> authorities;
}
