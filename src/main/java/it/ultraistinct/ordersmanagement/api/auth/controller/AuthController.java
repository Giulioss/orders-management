package it.ultraistinct.ordersmanagement.api.auth.controller;

import it.ultraistinct.ordersmanagement.api.auth.facade.AuthServiceFacade;
import it.ultraistinct.ordersmanagement.api.auth.request.LoginRequest;
import it.ultraistinct.ordersmanagement.api.auth.response.LoginResponse;
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
    public Mono<ResponseEntity<LoginResponse>> authenticate(@RequestBody @Validated LoginRequest request) {
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
    public Mono<ResponseEntity<Void>> registerNewAdmin(@RequestBody @Valid LoginRequest authenticationRequest) {
        /* TODO Giulio Galletti 25/06/2024: Possibilità di creare un Advice per evitare questi try/catch??
            Credo di si, ma ora non ho voglia
        */

        return this.authServiceFacade.registerNewAdmin(authenticationRequest)
                .then(Mono.just(ResponseEntity.ok().build()));
    }
}
