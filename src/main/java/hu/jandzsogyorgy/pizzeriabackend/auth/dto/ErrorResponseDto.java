package hu.jandzsogyorgy.pizzeriabackend.auth.dto;

public record ErrorResponseDto(
        int code,
        String error,
        String message
) {
}
