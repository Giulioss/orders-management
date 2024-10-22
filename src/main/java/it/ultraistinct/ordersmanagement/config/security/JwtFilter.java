package it.ultraistinct.ordersmanagement.config.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JwtFilter implements WebFilter {

    private final JwtService jwtService;
    private final ReactiveUserDetailsService userDetailsService;

    @Override
    @NonNull
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (request.getURI().getPath().contains("/auth/login")) {
            return chain.filter(exchange);
        }

        final String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        final String jwt = authHeader.substring(7);
        final String userName = this.jwtService.extractUsername(jwt);

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            return this.userDetailsService.findByUsername(userName)
                    .filter(userDetails -> this.jwtService.isTokenValid(jwt, userDetails))
                    .flatMap(userDetails -> {
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                        final SecurityContext securityContext = new SecurityContextImpl(auth);

                        return chain.filter(exchange)
                                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));

                    });
        }

        return chain.filter(exchange);
    }
}
