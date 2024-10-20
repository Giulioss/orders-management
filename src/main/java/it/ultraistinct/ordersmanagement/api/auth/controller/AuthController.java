package it.ultraistinct.ordersmanagement.api.auth.controller;

import it.ultraistinct.ordersmanagement.api.auth.facade.AuthServiceFacade;
import it.ultraistinct.ordersmanagement.api.auth.request.LoginRequest;
import it.ultraistinct.ordersmanagement.api.auth.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceFacade authServiceFacade;

    @PostMapping("/login")
    public ResponseEntity<Mono<LoginResponse>> authenticate(@RequestBody @Validated LoginRequest request) {
        return ResponseEntity.ok(this.authServiceFacade.authenticate(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<Mono<Boolean>> validateToken(HttpServletRequest request) {
        return ResponseEntity.ok(this.authServiceFacade.validateToken(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerNewAdmin(@RequestBody @Valid LoginRequest authenticationRequest) {
        /* TODO Giulio Galletti 25/06/2024: Possibilità di creare un Advice per evitare questi try/catch??
            Credo di si, ma ora non ho voglia
        */
        this.authServiceFacade.registerNewAdmin(authenticationRequest).subscribe();

        return ResponseEntity.ok().build();
    }
}
