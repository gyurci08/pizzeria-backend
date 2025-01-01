package hu.jandzsogyorgy.pizzeriabackend.auth.map;

import hu.jandzsogyorgy.pizzeriabackend.auth.dto.RoleDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.entities.Role;
import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.GenericMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface RoleMapper extends GenericMapper<Role, RoleDto> {

}
