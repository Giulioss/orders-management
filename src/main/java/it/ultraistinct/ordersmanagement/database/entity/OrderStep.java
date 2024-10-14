package it.ultraistinct.ordersmanagement.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class OrderStep {

    @Id
    private Long id;

    private Long orderId;
}

