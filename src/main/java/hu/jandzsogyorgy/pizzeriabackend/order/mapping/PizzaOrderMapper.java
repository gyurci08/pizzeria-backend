package hu.jandzsogyorgy.pizzeriabackend.order.mapping;

import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.GenericMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import hu.jandzsogyorgy.pizzeriabackend.order.dto.PizzaOrderDto;
import hu.jandzsogyorgy.pizzeriabackend.order.entity.PizzaOrder;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface PizzaOrderMapper extends GenericMapper<PizzaOrder, PizzaOrderDto> {
}
