package it.ultraistinct.ordersmanagement.api.order.facades;

import it.ultraistinct.ordersmanagement.api.order.requests.OrdersFilterRequest;
import it.ultraistinct.ordersmanagement.api.order.responses.OrderResponse;
import it.ultraistinct.ordersmanagement.api.order.responses.OrdersTableResponse;
import it.ultraistinct.ordersmanagement.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderServiceFacade {

    private final OrderService orderService;

    public Flux<OrderResponse> getTableOrders(OrdersFilterRequest filterRequest) {
        return this.orderService.getTableOrders(filterRequest)
                .map(a -> {
                    var response = new OrderResponse();
                    response.setId(a.getId());
                    response.setStartDateTime(a.getStartDateTime());
                    response.setEndDateTime(a.getEndDateTime());
                    response.setOrderStatus(a.getOrderStatus());
                    response.setNote(a.getNote());
                    return response;
                });
    }

    public Mono<OrdersTableResponse> getData(Pageable pageable, String sortBy, String direction) {
        Flux<OrderResponse> res = this.orderService.getData(pageable, sortBy, direction)
                .map(a -> {
                    var response = new OrderResponse();
                    response.setId(a.getId());
                    response.setStartDateTime(a.getStartDateTime());
                    response.setEndDateTime(a.getEndDateTime());
                    response.setOrderStatus(a.getOrderStatus());
                    response.setNote(a.getNote());
                    return response;
                });

        Mono<Long> totalElementMono = res.count();

        return totalElementMono
                .zipWith(res.collectList(), OrdersTableResponse::new
        );
    }
}