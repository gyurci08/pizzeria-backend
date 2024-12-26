package hu.jandzsogyorgy.pizzeriabackend.exception;

import hu.jandzsogyorgy.pizzeriabackend.auth.dto.LoginErrorResponseDto;
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
    public LoginErrorResponseDto handleAuthenticationException(AuthenticationException ex) {
        return new LoginErrorResponseDto(ex.getMessage());
    }


}
