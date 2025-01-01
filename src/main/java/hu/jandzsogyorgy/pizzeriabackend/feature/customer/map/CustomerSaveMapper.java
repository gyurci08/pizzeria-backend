package hu.jandzsogyorgy.pizzeriabackend.feature.customer.map;

import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.EntityMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto.CustomerSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfig.class)
public interface CustomerSaveMapper extends EntityMapper<Customer, CustomerSaveDto> {

    @Mapping(target = "id", ignore = true)
    @Override
    Customer toEntity(CustomerSaveDto dto);
}
