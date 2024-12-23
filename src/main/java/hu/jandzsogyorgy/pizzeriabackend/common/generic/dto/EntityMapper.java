package hu.jandzsogyorgy.pizzeriabackend.common.generic.dto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO -> entity mapper
 *
 * @param <E>Entity
 * @param <D>DTO
 */
public interface EntityMapper<E, D> {
    E toEntity(D dto);

    default List<E> toEntity(List<D> dtos) {
        if (dtos == null) {
            return List.of();
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
