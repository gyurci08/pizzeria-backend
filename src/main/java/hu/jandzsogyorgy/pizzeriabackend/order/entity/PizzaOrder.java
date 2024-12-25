package hu.jandzsogyorgy.pizzeriabackend.order.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

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