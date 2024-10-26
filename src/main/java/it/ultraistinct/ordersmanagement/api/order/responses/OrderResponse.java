package it.ultraistinct.ordersmanagement.api.order.responses;

import it.ultraistinct.ordersmanagement.domain.enums.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private OrderStatusEnum orderStatus;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String note;
    private int numbersOfStep = 0;
}
