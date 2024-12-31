package hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.service;

import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.dto.MenuItemDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.map.MenuItemMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    public List<MenuItemDto> listMenuItems() {
        return menuItemMapper.toDto(menuItemRepository.findAll());
    }

}
