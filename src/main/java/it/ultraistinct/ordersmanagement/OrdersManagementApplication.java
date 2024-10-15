package it.ultraistinct.ordersmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class OrdersManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersManagementApplication.class, args);
    }

}
