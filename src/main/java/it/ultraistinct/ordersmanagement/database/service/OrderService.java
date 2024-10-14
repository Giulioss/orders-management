package it.ultraistinct.ordersmanagement.database.service;

import it.ultraistinct.ordersmanagement.database.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
}
