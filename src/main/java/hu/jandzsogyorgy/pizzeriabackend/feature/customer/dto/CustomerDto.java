package hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto;

public record CustomerDto(
        Long id,
        String name,
        String phone,
        String address,
        Long userId
) {
}
