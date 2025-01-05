package hu.jandzsogyorgy.pizzeriabackend.feature.pizzaIngredient.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PizzaIngredient {
    @Id
    private Long id;
    private Long pizzaId;
    private Long ingredientId;
    private int quantity;
}
