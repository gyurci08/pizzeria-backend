package hu.jandzsogyorgy.pizzeriabackend.feature.order.map;


import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.EntityMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.entity.Order;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.map.OrderItemSaveMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfig.class, uses = {OrderItemSaveMapper.class})
public interface OrderSaveMapper extends EntityMapper<Order, OrderSaveDto> {

    @Mapping(target="id", ignore = true)
    @Mapping(target="orderDate", ignore = true)
    @Mapping(target="totalAmount", ignore = true)
    @Override
    Order toEntity(OrderSaveDto dto);

}
