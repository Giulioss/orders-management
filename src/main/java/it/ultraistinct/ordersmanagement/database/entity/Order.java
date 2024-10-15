package it.ultraistinct.ordersmanagement.database.entity;

import it.ultraistinct.ordersmanagement.database.enums.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class Order {

    @Id
    private Long id;

    private Long clientId;

    private String orderStatus;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String note;

    public OrderStatusEnum getOrderStatus() {
        return OrderStatusEnum.fromString(this.orderStatus);
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus.name();
    }
}
