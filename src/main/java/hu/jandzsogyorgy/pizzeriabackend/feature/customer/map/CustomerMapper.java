package hu.jandzsogyorgy.pizzeriabackend.feature.customer.map;

import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.GenericMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto.CustomerDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface CustomerMapper extends GenericMapper<Customer, CustomerDto> {

}
