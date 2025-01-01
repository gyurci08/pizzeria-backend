package hu.jandzsogyorgy.pizzeriabackend.feature.order.dto;

import hu.jandzsogyorgy.pizzeriabackend.feature.order.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDto(
        Long id,
        Long customerId,
        LocalDateTime orderDate,
        BigDecimal totalAmount,
        Status status
) {
}
