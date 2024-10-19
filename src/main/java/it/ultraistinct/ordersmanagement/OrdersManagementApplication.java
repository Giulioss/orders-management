package it.ultraistinct.ordersmanagement;

import it.ultraistinct.ordersmanagement.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableConfigurationProperties(JwtProperties.class)
public class OrdersManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersManagementApplication.class, args);
    }

}
