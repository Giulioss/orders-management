package it.ultraistinct.ordersmanagement.domain.order.repository;

import it.ultraistinct.ordersmanagement.domain.order.entity.Orders;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface OrderRepository extends ReactiveCrudRepository<Orders, Long> {

    Flux<Orders> findAllByOrderStatusIsIn(final List<String> orderStatus);
}
