package hu.jandzsogyorgy.pizzeriabackend.auth.dto;

public record RegisterRequestDto(
        String name,
        String email,
        String username,
        String password,
        String phone,
        String address
) {
}
