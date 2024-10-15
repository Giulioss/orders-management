package it.ultraistinct.ordersmanagement.domain.order_step.service;

import it.ultraistinct.ordersmanagement.domain.order_step.repository.OrderStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStepService {

    private final OrderStepRepository orderStepRepository;
}
