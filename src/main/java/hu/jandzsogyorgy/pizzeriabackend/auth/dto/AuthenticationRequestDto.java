package hu.jandzsogyorgy.pizzeriabackend.auth.dto;

public record AuthenticationRequestDto(
        String username,
        String password
) {
}
