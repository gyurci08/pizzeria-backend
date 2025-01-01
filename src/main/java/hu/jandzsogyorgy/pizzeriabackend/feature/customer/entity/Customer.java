package hu.jandzsogyorgy.pizzeriabackend.feature.customer.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Customer {
    @Id
    private Long id;
    private String name;
    private String phone;
    private String address;
    private Long userId;
}
