package it.ultraistinct.ordersmanagement.api.order.controllers;

import it.ultraistinct.ordersmanagement.api.order.facades.OrderServiceFacade;
import it.ultraistinct.ordersmanagement.api.order.requests.OrdersFilterRequest;
import it.ultraistinct.ordersmanagement.api.order.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceFacade orderServiceFacade;

    @PostMapping("/find")
    public Flux<OrderResponse> getTableOrders(@RequestBody @Validated OrdersFilterRequest filterRequest) {
        return this.orderServiceFacade.getTableOrders(filterRequest);
    }

}
