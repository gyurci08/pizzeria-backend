package hu.jandzsogyorgy.pizzeriabackend.feature.order.dto;

import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.dto.OrderItemSaveDto;

import java.util.List;

public record OrderSaveDto(
        Long customerId,
        String status,

        List<OrderItemSaveDto> orderItems
) {
}
