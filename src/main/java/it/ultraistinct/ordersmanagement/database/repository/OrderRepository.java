package it.ultraistinct.ordersmanagement.database.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<OrderRepository, Long> {
}
