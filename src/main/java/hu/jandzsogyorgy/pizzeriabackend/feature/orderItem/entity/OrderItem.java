package hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class OrderItem {
    @Id
    private Long id;
    private Long orderId;
    private Long menuItemId;
    private int quantity;
    private BigDecimal price;
}
