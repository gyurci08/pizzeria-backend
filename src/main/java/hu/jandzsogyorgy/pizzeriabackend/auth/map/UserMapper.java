package hu.jandzsogyorgy.pizzeriabackend.auth.map;

import hu.jandzsogyorgy.pizzeriabackend.auth.dto.UserDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.entities.User;
import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.GenericMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface UserMapper extends GenericMapper<User, UserDto> {

}
