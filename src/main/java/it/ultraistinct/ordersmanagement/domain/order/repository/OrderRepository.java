package it.ultraistinct.ordersmanagement.domain.order.repository;

import it.ultraistinct.ordersmanagement.domain.order.entity.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {

    Flux<Order> findAllByOrderStatus(final String orderStatus);
}
