package it.ultraistinct.ordersmanagement.domain.order.service;

import it.ultraistinct.ordersmanagement.domain.order.entity.Orders;
import it.ultraistinct.ordersmanagement.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Flux<Orders> getData(String sortBy, String direction) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Flux<Orders> dataFlux = orderRepository.findAll()
                .sort((data1, data2) -> sortDirection.isAscending()
                        ? sortBy(data1, data2, sortBy)
                        : sortBy(data2, data1, sortBy)
                );

        return dataFlux.subscribeOn(Schedulers.boundedElastic());
    }

    private int sortBy(final Orders order1, final Orders order2, final String sortBy) {
        if (StringUtils.isBlank(sortBy)) {
            return 0;
        }

        if (order1 == null) {
            return -1;
        }

        if (order2 == null) {
            return 1;
        }

        return switch (sortBy) {
            case "id" -> order1.getId().compareTo(order2.getId());
            case "startDateTime" -> order1.getStartDateTime().compareTo(order2.getStartDateTime());
            case "endDateTime" -> order1.getEndDateTime().compareTo(order2.getEndDateTime());
            case "status" -> order1.getOrderStatus().compareTo(order2.getOrderStatus());
            default -> 0;
        };
    }
}
