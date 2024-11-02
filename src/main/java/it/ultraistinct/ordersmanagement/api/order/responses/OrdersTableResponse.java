package it.ultraistinct.ordersmanagement.api.order.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersTableResponse {
    private Long totalElements;
    private List<OrderResponse> orderResponseList;
}
