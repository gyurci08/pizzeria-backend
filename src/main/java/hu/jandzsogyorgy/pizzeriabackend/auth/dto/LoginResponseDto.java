package hu.jandzsogyorgy.pizzeriabackend.auth.dto;

public record LoginResponseDto(
        String accessToken,
        String refreshToken
) {
}
