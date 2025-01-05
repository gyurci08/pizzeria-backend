package hu.jandzsogyorgy.pizzeriabackend.auth.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
public class User {
    @Id
    private Long id;
    private String email;
    private String username;
    private String password;
    
    @Transient
    private List<Role> roles;
}
