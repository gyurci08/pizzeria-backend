package hu.jandzsogyorgy.pizzeriabackend.order.dto;

import java.util.Date;

public record PizzaOrderDto(
        Long id,
        Long customerId,
        Date orderDate,
        Float totalAmount,
        String status
) {
}
