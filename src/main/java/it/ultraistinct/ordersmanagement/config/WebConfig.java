package it.ultraistinct.ordersmanagement.config;

import it.ultraistinct.ordersmanagement.config.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class WebConfig {

    private final JwtFilter jwtFilter;

    // TODO Giulio Galletti 15/10/2024: Inserire i giusti endpoints
    private static final String[] publicEndpoints = new String[]{
            "/login/**"
    };

    // TODO Giulio Galletti 15/10/2024: Inserire i giusti endpoints
    private static final String[] privateEndpoints = new String[]{
            "/**"
    };

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(ex -> ex
                        .pathMatchers(publicEndpoints).permitAll()
                        .pathMatchers(privateEndpoints).authenticated()
                )
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance()) // Stateless session
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.FIRST)
                .build();
    }
}
