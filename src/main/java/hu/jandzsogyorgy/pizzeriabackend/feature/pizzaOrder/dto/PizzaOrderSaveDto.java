package hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.dto;

import java.util.Date;

public record PizzaOrderSaveDto(
        Long customerId,
        Date orderDate,
        Float totalAmount,
        String status
) {
}
