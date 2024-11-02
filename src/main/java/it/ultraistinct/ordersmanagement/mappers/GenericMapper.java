package it.ultraistinct.ordersmanagement.mappers;

import it.ultraistinct.ordersmanagement.api.order.responses.OrderResponse;
import it.ultraistinct.ordersmanagement.domain.order.entity.Orders;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class GenericMapper {

    public abstract OrderResponse toDto(Orders order);
}
