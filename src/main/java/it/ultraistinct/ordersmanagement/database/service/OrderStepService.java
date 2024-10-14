package it.ultraistinct.ordersmanagement.database.service;

import it.ultraistinct.ordersmanagement.database.repository.OrderStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStepService {

    private final OrderStepRepository orderStepRepository;
}
