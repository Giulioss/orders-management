package it.ultraistinct.ordersmanagement.domain.order.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<OrderRepository, Long> {
}
