package it.ultraistinct.ordersmanagement.database.entity;

import it.ultraistinct.ordersmanagement.database.enums.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Order {

    @Id
    private Long id;

    private Long clientId;

    private OrderStatusEnum orderStatus;
    private OffsetDateTime startDateTime;
    private String note;
}
