package it.ultraistinct.ordersmanagement.domain.order_image.service;

import it.ultraistinct.ordersmanagement.domain.order_image.repository.OrderImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderImageService {

    private final OrderImageRepository orderImageRepository;
}
