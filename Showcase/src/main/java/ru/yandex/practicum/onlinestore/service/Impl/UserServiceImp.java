package ru.yandex.practicum.onlinestore.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.yandex.practicum.onlinestore.dto.UserDto;
import ru.yandex.practicum.onlinestore.entity.User;
import ru.yandex.practicum.onlinestore.mapper.UserDtoMapper;
import ru.yandex.practicum.onlinestore.repository.UserRepository;
import ru.yandex.practicum.onlinestore.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    private final UserDtoMapper userDtoMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<Void> create(UserDto obj) {
        User user = userDtoMapper.toEntity(obj);
        userRepository.save(makeHashPassword(user)).doOnNext(usr ->
                log.debug("User with id {} has saved", usr.getId())
        );
        return Mono.empty();
    }

    //TODO Add read, update, delete

    private User makeHashPassword(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return user;
    }
}