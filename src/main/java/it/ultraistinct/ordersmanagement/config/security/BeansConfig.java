package it.ultraistinct.ordersmanagement.config.security;

import it.ultraistinct.ordersmanagement.config.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final SecurityProperties securityProperties;

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList(securityProperties.getCorsAllowedOrigin()));
        config.setAllowedHeaders(List.of(
                "*"
        ));
        config.setAllowedMethods(List.of(
                "*"
        ));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
