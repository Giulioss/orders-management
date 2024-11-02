package it.ultraistinct.ordersmanagement.api.order.facades;

import it.ultraistinct.ordersmanagement.api.order.responses.OrderResponse;
import it.ultraistinct.ordersmanagement.api.order.responses.OrdersTableResponse;
import it.ultraistinct.ordersmanagement.domain.order.entity.Orders;
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

    public Mono<OrdersTableResponse> getData(Pageable pageable, String sortBy, String direction) {
        Flux<Orders> res = this.orderService.getData(sortBy, direction);
        Mono<Long> totalElementMono = res.count();

        Flux<OrderResponse> resDto = res
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .take(pageable.getPageSize())
                .map(a -> {
                    var response = new OrderResponse();
                    response.setId(a.getId());
                    response.setStartDateTime(a.getStartDateTime());
                    response.setEndDateTime(a.getEndDateTime());
                    response.setOrderStatus(a.getOrderStatus());
                    response.setNote(a.getNote());
                    return response;
                });

        return totalElementMono
                .zipWith(resDto.collectList(), OrdersTableResponse::new
        );
    }
}
