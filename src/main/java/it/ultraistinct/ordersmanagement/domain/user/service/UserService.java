package it.ultraistinct.ordersmanagement.domain.user.service;

import it.ultraistinct.ordersmanagement.domain.user.entity.User;
import it.ultraistinct.ordersmanagement.domain.user.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> findByUsername(@NonNull String username) {
        return this.userRepository.findByUsername(username);
    }
}
