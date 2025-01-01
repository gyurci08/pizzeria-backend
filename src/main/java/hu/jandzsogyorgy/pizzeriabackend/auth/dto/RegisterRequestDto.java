package hu.jandzsogyorgy.pizzeriabackend.auth.dto;

public record RegisterRequestDto(
        String email,
        String username,
        String password
) {
}
