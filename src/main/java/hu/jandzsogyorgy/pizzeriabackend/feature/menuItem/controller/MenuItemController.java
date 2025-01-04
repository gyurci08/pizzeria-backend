package hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.controller;

import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.dto.MenuItemDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.service.MenuItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//
@Slf4j
@RestController
@RequestMapping(value = "/api/menu-items", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    @PreAuthorize("permitAll()")
    @GetMapping
    public List<MenuItemDto> listMenuItems() {
        log.info("listMenuItems");
        return menuItemService.listMenuItems();
    }

}
