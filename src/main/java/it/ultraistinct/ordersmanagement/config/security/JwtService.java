package it.ultraistinct.ordersmanagement.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import it.ultraistinct.ordersmanagement.config.SecurityProperties;
import it.ultraistinct.ordersmanagement.domain.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final SecurityProperties securityProperties;

    private final ReactiveUserDetailsService reactiveUserDetailsService;

    public Mono<Boolean> validateTokenFromRequest(@NonNull ServerWebExchange exchange) {
        final String authHeader = this.extractAuthorizationHeader(exchange.getRequest());

        if (StringUtils.isBlank(authHeader)) {
            log.warn("Authorization header is null");
            return Mono.just(false);
        }

        final String jwt = this.extractJwtTokenFromStringHeader(authHeader);
        final String userName = extractUsername(jwt);

        if (StringUtils.isBlank(userName)) {
            return Mono.just(false);
        }

        var userInContext = ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(authentication -> {
                    if (authentication == null || !authentication.isAuthenticated()) {
                        return false;
                    }

                    String usernameInContext = authentication.getName();
                    return usernameInContext.equals(userName);
                })
                .defaultIfEmpty(false);

        return userInContext
                .flatMap(isValid -> {
                    if (BooleanUtils.isNotTrue(isValid)) {
                        return Mono.just(false);
                    }

                    return reactiveUserDetailsService.findByUsername(userName)
                            .filter(userDetails -> this.isTokenValid(jwt, userDetails))
                            .hasElement();
                });
    }

    public String extractJwtTokenFromStringHeader(String authHeader) {
        return authHeader.substring(7);
    }

    public String extractAuthorizationHeader(ServerHttpRequest exchangeRequest) {
        return exchangeRequest.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String buildToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + securityProperties.getJwt().getExpiration()))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(securityProperties.getJwt().getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
