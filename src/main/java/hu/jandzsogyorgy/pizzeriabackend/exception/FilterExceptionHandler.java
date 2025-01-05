package hu.jandzsogyorgy.pizzeriabackend.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.jandzsogyorgy.pizzeriabackend.auth.dto.ErrorResponseDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.exception.AuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Filter chain is before controller layer
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // Ensure that the handler runs first in the chain
@RequiredArgsConstructor
public class FilterExceptionHandler extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    private void handleException(HttpServletResponse response, HttpStatus status, Exception ex) throws IOException {
        response.setContentType("application/json");
        response.setStatus(status.value());

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage()
        );
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthenticationException ex) {
            handleException(response, HttpStatus.UNAUTHORIZED, ex);
        } catch (Exception ex) {
            handleException(response, HttpStatus.INTERNAL_SERVER_ERROR, ex);
        }
    }
}


