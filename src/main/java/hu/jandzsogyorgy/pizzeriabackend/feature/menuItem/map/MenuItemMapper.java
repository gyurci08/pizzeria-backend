package hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.map;

import hu.jandzsogyorgy.pizzeriabackend.common.generic.dto.GenericMapper;
import hu.jandzsogyorgy.pizzeriabackend.config.MappingConfig;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.dto.MenuItemDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.entity.MenuItem;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface MenuItemMapper extends GenericMapper<MenuItem, MenuItemDto> {
}
