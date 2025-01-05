package hu.jandzsogyorgy.pizzeriabackend.feature.pizza.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Pizza {
    @Id
    private Long id;
    private Long menuItemId;
}
