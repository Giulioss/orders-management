package it.ultraistinct.ordersmanagement.domain.order.service;

import it.ultraistinct.ordersmanagement.api.order.requests.OrdersFilterRequest;
import it.ultraistinct.ordersmanagement.domain.order.entity.Order;
import it.ultraistinct.ordersmanagement.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Flux<Order> getTableOrders(OrdersFilterRequest filterRequest) {
        return this.orderRepository.findAllByOrderStatus(filterRequest.getOrderStatus().name())
                .skip((long) filterRequest.getPage() * filterRequest.getPageSize())
                .take(filterRequest.getPageSize());
    }
}
