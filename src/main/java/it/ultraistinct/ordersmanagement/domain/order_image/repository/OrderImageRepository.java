package it.ultraistinct.ordersmanagement.domain.order_image.repository;

import it.ultraistinct.ordersmanagement.domain.order_image.entity.OrderImage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderImageRepository extends ReactiveCrudRepository<OrderImage, Long> {
}
