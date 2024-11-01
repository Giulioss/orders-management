package it.ultraistinct.ordersmanagement.api.order.controllers;

import it.ultraistinct.ordersmanagement.api.order.facades.OrderServiceFacade;
import it.ultraistinct.ordersmanagement.api.order.responses.OrderResponse;
import it.ultraistinct.ordersmanagement.api.order.responses.OrdersTableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceFacade orderServiceFacade;

    @GetMapping("/find")
    public Mono<OrdersTableResponse> getTableOrders(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(required = false) String sortBy,
                                                    @RequestParam(required = false) String direction) {

        Pageable pageable = PageRequest.of(page, size);

        return this.orderServiceFacade.getData(pageable, sortBy, direction);
    }

}
