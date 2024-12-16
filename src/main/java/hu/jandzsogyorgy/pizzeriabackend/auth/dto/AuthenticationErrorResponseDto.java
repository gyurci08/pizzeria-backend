package hu.jandzsogyorgy.pizzeriabackend.auth.dto;

public record AuthenticationErrorResponseDto(
        String error,
        String message
) {
}
