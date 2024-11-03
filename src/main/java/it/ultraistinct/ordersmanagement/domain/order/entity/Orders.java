package it.ultraistinct.ordersmanagement.domain.order.entity;

import it.ultraistinct.ordersmanagement.domain.order_image.entity.OrderImage;
import it.ultraistinct.ordersmanagement.domain.order_step.entity.OrderStep;
import it.ultraistinct.ordersmanagement.enums.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Orders {

    @Id
    private Long id;

    private Long clientId;
    private Long startUserId;
    private Long endUserId;

    private String orderStatus;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String note;

    @Transient
    private List<OrderStep> orderSteps;

    @Transient
    private List<OrderImage> orderImages;

    public OrderStatusEnum getOrderStatus() {
        return OrderStatusEnum.fromString(this.orderStatus);
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus.name();
    }
}
