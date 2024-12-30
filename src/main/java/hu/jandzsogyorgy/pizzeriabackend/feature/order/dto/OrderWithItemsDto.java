package hu.jandzsogyorgy.pizzeriabackend.feature.order.dto;

import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.dto.OrderItemDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderWithItemsDto(
        Long id,
        Long customerId,
        LocalDateTime orderDate,
        BigDecimal totalAmount,
        String status,

        List<OrderItemDto> orderItems
) {
}
