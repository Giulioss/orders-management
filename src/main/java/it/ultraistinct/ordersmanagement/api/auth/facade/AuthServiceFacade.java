package it.ultraistinct.ordersmanagement.api.auth.facade;

import it.ultraistinct.ordersmanagement.api.auth.request.LoginRequest;
import it.ultraistinct.ordersmanagement.api.auth.response.LoginResponse;
import it.ultraistinct.ordersmanagement.config.security.JwtService;
import it.ultraistinct.ordersmanagement.domain.user.entity.User;
import it.ultraistinct.ordersmanagement.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthServiceFacade {

    private final ReactiveAuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public Mono<LoginResponse> authenticate(LoginRequest request) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        )).map(authentication -> {
            var user = (User) authentication.getPrincipal();

            var jwtToken = jwtService.buildToken(user);
            return LoginResponse.builder()
                    .token(jwtToken)
                    .build();
        });

    }

    public Mono<Boolean> validateToken(@NonNull HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isBlank(authHeader)) {
            log.warn("Authorization header is null");
            return Mono.just(false);
        }

        return jwtService.validateTokenFromAuthHeader(authHeader);
    }


    public Mono<Void> registerNewAdmin(LoginRequest loginRequest) {

        return userService.findByUsername(loginRequest.getUsername())
                .flatMap(user -> Mono.error(new RuntimeException("Utente già registrato")))
                .switchIfEmpty(
                        exe(loginRequest)
                ).then();
    }

    private Mono<Void> exe(LoginRequest loginRequest) {
        User newUser = new User();
        newUser.setUsername(loginRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        newUser.setEnabled(true);
        newUser.setRole("ADMIN");

        return userService.save(newUser).then();
    }
}
