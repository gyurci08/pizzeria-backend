package hu.jandzsogyorgy.pizzeriabackend.common.generic.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface JsonDtoMapper {
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    default <T> T jsonToDto(Object obj, Class<T> dtoClass) {
        try {
            if (obj instanceof String) {
                return OBJECT_MAPPER.readValue((String) obj, dtoClass);
            } else {
                return OBJECT_MAPPER.convertValue(obj, dtoClass);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error mapping JSON to DTO", e);
        }
    }

    default String dtoToJson(Object dto) {
        try {
            return OBJECT_MAPPER.writeValueAsString(dto);
        } catch (IOException e) {
            throw new RuntimeException("Error mapping DTO to JSON", e);
        }
    }
}
