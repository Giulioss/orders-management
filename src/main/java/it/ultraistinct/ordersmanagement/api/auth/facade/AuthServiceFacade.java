package it.ultraistinct.ordersmanagement.api.auth.facade;

import it.ultraistinct.ordersmanagement.api.auth.request.AuthRequest;
import it.ultraistinct.ordersmanagement.api.auth.response.AuthResponse;
import it.ultraistinct.ordersmanagement.config.security.JwtService;
import it.ultraistinct.ordersmanagement.domain.enums.UserRoleEnum;
import it.ultraistinct.ordersmanagement.domain.user.entity.User;
import it.ultraistinct.ordersmanagement.domain.user.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthServiceFacade {

    private final ReactiveAuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public Mono<AuthResponse> authenticate(AuthRequest request) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        )).flatMap(authentication -> this.userService.findByUsername(request.getUsername()))
           .map(user -> {

            var jwtToken = jwtService.buildToken(user);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        });

    }

    public Mono<Boolean> validateToken(@NonNull ServerWebExchange exchange) {
        return jwtService.validateTokenFromRequest(exchange);
    }


    public Mono<Void> registerNewAdmin(AuthRequest authRequest) {

        return userService.findByUsername(authRequest.getUsername())
                .flatMap(user -> Mono.error(new RuntimeException("Utente già registrato")))
                .switchIfEmpty(
                        createUserAdmin(authRequest)
                ).then();
    }

    private Mono<Void> createUserAdmin(AuthRequest authRequest) {
        User newUser = new User();
        newUser.setUsername(authRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        newUser.setEnabled(true);
        newUser.setRole(UserRoleEnum.ADMIN);

        return userService.save(newUser).then();
    }
}
