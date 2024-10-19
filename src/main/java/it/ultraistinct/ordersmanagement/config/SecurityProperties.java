package it.ultraistinct.ordersmanagement.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.security")
@Getter
@AllArgsConstructor
public class SecurityProperties {

    private String corsAllowedOrigin;
    private Jwt jwtProperties;

    @Getter
    @AllArgsConstructor
    public static class Jwt {
        private String secretKey;
        private long expiration;
    }
}
