package it.ultraistinct.ordersmanagement.domain.order_step.repository;

import it.ultraistinct.ordersmanagement.domain.order_step.entity.OrderStep;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderStepRepository extends ReactiveCrudRepository<OrderStep, Long> {
}
