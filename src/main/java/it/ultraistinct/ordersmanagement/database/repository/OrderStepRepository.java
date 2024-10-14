package it.ultraistinct.ordersmanagement.database.repository;

import it.ultraistinct.ordersmanagement.database.entity.OrderStep;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderStepRepository extends ReactiveCrudRepository<OrderStep, Long> {
}
