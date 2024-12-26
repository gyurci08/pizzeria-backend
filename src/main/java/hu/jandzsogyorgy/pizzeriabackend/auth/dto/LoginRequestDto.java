package hu.jandzsogyorgy.pizzeriabackend.auth.dto;

public record LoginRequestDto(
        String username,
        String password
) {
}
