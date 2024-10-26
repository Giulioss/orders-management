package it.ultraistinct.ordersmanagement.domain.order_step_image.repository;

import it.ultraistinct.ordersmanagement.domain.order_step_image.entity.OrderStepImage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStepImageRepository extends ReactiveCrudRepository<OrderStepImage, Long> {
}
