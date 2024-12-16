package hu.jandzsogyorgy.pizzeriabackend.auth.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserRole {
    private Long userId;
    private Long roleId;
}