package it.ultraistinct.ordersmanagement.api.auth.controller;

import it.ultraistinct.ordersmanagement.api.auth.facade.AuthServiceFacade;
import it.ultraistinct.ordersmanagement.api.auth.request.AuthRequest;
import it.ultraistinct.ordersmanagement.api.auth.response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceFacade authServiceFacade;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> authenticate(@RequestBody @Validated AuthRequest request) {
        return this.authServiceFacade.authenticate(request)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/validate")
    public Mono<ResponseEntity<Boolean>> validateToken(HttpServletRequest request) {
        return this.authServiceFacade.validateToken(request)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<Void>> registerNewAdmin(@RequestBody @Valid AuthRequest authenticationRequest) {
        return this.authServiceFacade.registerNewAdmin(authenticationRequest)
                .then(Mono.just(ResponseEntity.ok().build()));
    }
}
