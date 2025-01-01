package hu.jandzsogyorgy.pizzeriabackend.auth.dto;

public record RegisterResponseDto(
        String name,
        String email,
        String username,
        String phone,
        String address
) {
}
