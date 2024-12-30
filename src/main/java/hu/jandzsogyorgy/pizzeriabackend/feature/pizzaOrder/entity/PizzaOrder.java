package hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Data
public class PizzaOrder {
    @Id
    private Long id;
    private Long customerId;
    private Date orderDate;
    private Float totalAmount;
    private String status;
}
