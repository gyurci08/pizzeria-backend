package hu.jandzsogyorgy.pizzeriabackend.feature.ingredient.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Ingredient {
    @Id
    private Long id;
    private String name;
    private int stockQuantity;
}
