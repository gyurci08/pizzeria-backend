package hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.map;

import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.GenericMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.dto.OrderItemDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfig.class)
public interface OrderItemMapper extends GenericMapper<OrderItem, OrderItemDto> {
}
