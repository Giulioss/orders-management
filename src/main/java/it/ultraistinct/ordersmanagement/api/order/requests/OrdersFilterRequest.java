package it.ultraistinct.ordersmanagement.api.order.requests;

import it.ultraistinct.ordersmanagement.domain.enums.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersFilterRequest {

    private int page = 0;
    private int pageSize = 10;
    private List<OrderStatusEnum> orderStatusList;
}
