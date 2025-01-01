package hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.dto;

public record OrderItemSaveDto(
        Long menuItemId,
        int quantity
) {
}
