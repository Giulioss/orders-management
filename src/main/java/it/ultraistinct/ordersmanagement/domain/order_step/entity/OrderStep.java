package it.ultraistinct.ordersmanagement.domain.order_step.entity;

import it.ultraistinct.ordersmanagement.domain.order_step_image.entity.OrderStepImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
public class OrderStep {

    @Id
    private Long id;

    private Long orderId;

    private List<OrderStepImage> orderStepImages;
}

