package hu.jandzsogyorgy.pizzeriabackend.auth.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;

    @Transient
    private List<Role> roles;
}
