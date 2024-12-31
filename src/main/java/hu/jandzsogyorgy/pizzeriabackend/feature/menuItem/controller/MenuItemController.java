package hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.controller;

import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.dto.MenuItemDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.service.MenuItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//
@RestController
@RequestMapping(value = "/api/menu-items", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemDto> listMenuItems() {
        return menuItemService.listMenuItems();
    }

}
