package it.ultraistinct.ordersmanagement.domain.order.service;

import it.ultraistinct.ordersmanagement.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
}