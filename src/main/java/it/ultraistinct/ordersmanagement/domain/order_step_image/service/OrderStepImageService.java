package it.ultraistinct.ordersmanagement.domain.order_step_image.service;

import it.ultraistinct.ordersmanagement.domain.order_step_image.repository.OrderStepImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStepImageService {

    private final OrderStepImageRepository orderStepImageRepository;
}
