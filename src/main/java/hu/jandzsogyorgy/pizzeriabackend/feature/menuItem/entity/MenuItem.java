package hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class MenuItem {
    @Id
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
}
