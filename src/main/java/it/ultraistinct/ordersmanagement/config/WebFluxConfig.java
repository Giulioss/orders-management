package it.ultraistinct.ordersmanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebFluxConfig implements WebFluxConfigurer {

    private final SecurityProperties securityProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(securityProperties.getCorsAllowedOrigin())
                .allowedMethods(CorsConfiguration.ALL)
                .exposedHeaders(HttpHeaders.AUTHORIZATION)
                .allowCredentials(true)
                .maxAge(3600);
    }

}
