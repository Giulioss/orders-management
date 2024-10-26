package it.ultraistinct.ordersmanagement.domain.order.service;

import it.ultraistinct.ordersmanagement.api.order.requests.OrdersFilterRequest;
import it.ultraistinct.ordersmanagement.domain.enums.OrderStatusEnum;
import it.ultraistinct.ordersmanagement.domain.order.entity.Orders;
import it.ultraistinct.ordersmanagement.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Flux<Orders> getTableOrders(OrdersFilterRequest filterRequest) {
        return this.orderRepository.findAllByOrderStatusIsIn(
                CollectionUtils.isEmpty(filterRequest.getOrderStatusList()) ? OrderStatusEnum.getAllValues() :
                        filterRequest.getOrderStatusList().stream().map(Enum::name).toList())
                .skip((long) filterRequest.getPage() * filterRequest.getPageSize())
                .take(filterRequest.getPageSize());
    }
}
