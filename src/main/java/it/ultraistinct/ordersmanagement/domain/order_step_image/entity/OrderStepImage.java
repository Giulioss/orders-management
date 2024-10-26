package it.ultraistinct.ordersmanagement.domain.order_step_image.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class OrderStepImage {

    @Id
    private Long id;

    private byte[] imageData;

    private Long orderStepId;
}
