package hu.jandzsogyorgy.pizzeriabackend.auth.dto;

import java.util.List;

public record UserDto(
        Long id,
        String email,
        String username,
        String password,

        List<RoleDto> roles
) {

}
