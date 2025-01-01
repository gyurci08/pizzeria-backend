package hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.map;

import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.EntityMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.dto.OrderItemSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfig.class)
public interface OrderItemSaveMapper extends EntityMapper<OrderItem, OrderItemSaveDto> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Override
    OrderItem toEntity(OrderItemSaveDto dto);
}
