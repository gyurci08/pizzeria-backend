package hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto;

public record CustomerSaveDto(
        String name,
        String phone,
        String address
) {
}
