package hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.map;


import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.EntityMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.dto.PizzaOrderSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.entity.PizzaOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfig.class)
public interface PizzaOrderSaveMapper extends EntityMapper<PizzaOrder, PizzaOrderSaveDto> {

    @Mapping(target = "id", ignore = true)
    @Override
    PizzaOrder toEntity(PizzaOrderSaveDto dto);
}
