package hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.map;

import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.GenericMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.dto.PizzaOrderDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.entity.PizzaOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfig.class)
public interface PizzaOrderMapper extends GenericMapper<PizzaOrder, PizzaOrderDto> {

    @Override
    @Mapping(target = "customerName", ignore = true)    // Will be handled in the service
    PizzaOrderDto toDto(PizzaOrder entity);
}
