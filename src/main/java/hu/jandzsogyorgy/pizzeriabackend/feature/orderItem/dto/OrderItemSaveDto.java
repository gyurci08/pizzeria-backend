package hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.dto;

import java.math.BigDecimal;

public record OrderItemSaveDto(
        Long orderId,
        Long menuItemId,
        int quantity,
        BigDecimal price
) {
}
