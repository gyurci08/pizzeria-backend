package hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.dto;

import java.math.BigDecimal;

public record MenuItemDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String category
) {
}
