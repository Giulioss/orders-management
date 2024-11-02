package it.ultraistinct.ordersmanagement.api.order.facades;

import it.ultraistinct.ordersmanagement.api.order.responses.OrderResponse;
import it.ultraistinct.ordersmanagement.api.order.responses.OrdersTableResponse;
import it.ultraistinct.ordersmanagement.domain.order.entity.Orders;
import it.ultraistinct.ordersmanagement.domain.order.service.OrderService;
import it.ultraistinct.ordersmanagement.mappers.GenericMapper;
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
    private final GenericMapper genericMapper;

    public Mono<OrdersTableResponse> getData(Pageable pageable, String sortBy, String direction) {
        Flux<Orders> res = this.orderService.getData(sortBy, direction);
        Mono<Long> totalElementMono = res.count();

        Flux<OrderResponse> resDto = res
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .take(pageable.getPageSize())
                .map(this.genericMapper::toDto);

        return totalElementMono
                .zipWith(resDto.collectList(), OrdersTableResponse::new
        );
    }
}
