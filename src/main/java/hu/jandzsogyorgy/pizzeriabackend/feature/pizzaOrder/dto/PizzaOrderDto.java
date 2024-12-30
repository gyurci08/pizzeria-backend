package hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.dto;

import java.beans.Transient;
import java.util.Date;

public record PizzaOrderDto(
        Long id,
        Long customerId,
        String customerName,
        Date orderDate,
        Float totalAmount,
        String status
) {
}
