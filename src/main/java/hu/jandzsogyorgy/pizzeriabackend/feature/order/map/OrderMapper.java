package hu.jandzsogyorgy.pizzeriabackend.feature.order.map;

import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.GenericMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderWithAllDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderWithItemsDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderWithNameDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.entity.Order;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MappingConfig.class)
public interface OrderMapper extends GenericMapper<Order, OrderDto> {

    @Override
    Order toEntity(OrderDto dto);


    @Override
    OrderDto toDto(Order entity);

    OrderWithItemsDto toDtoWithItems(Order entity, List<OrderItem> orderItems);

    OrderWithNameDto toDtoWithName(Order entity, String customerName);

    OrderWithAllDto toDtoWithAll(Order entity, List<OrderItem> orderItems, String customerName);
}
