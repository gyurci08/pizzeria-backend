package hu.jandzsogyorgy.pizzeriabackend.auth.dto;

public record LogoutRequestDto(
        String accessToken,
        String refreshToken
) {
}
