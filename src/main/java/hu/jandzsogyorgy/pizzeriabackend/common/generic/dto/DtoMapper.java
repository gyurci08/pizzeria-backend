package hu.jandzsogyorgy.pizzeriabackend.common.generic.dto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity -> DTO mapper
 *
 * @param <E>Entity
 * @param <D>DTO
 */
public interface DtoMapper<E, D> {
    D toDto(E entity);

    default List<D> toDto(List<E> entities) {
        if (entities == null) {
            return List.of();
        }

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
