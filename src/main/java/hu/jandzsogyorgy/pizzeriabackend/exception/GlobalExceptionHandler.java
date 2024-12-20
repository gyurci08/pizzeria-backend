package hu.jandzsogyorgy.pizzeriabackend.exception;

import hu.jandzsogyorgy.pizzeriabackend.auth.dto.AuthenticationErrorResponseDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public AuthenticationErrorResponseDto handleAuthenticationException(AuthenticationException ex) {
        return new AuthenticationErrorResponseDto(ex.toString(), ex.getMessage());
    }
}
