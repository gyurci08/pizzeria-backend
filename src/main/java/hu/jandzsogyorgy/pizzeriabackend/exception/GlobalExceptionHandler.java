package hu.jandzsogyorgy.pizzeriabackend.exception;

import hu.jandzsogyorgy.pizzeriabackend.auth.dto.ErrorResponseDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.exception.AuthenticationException;
import hu.jandzsogyorgy.pizzeriabackend.auth.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponseDto createErrorResponse(HttpStatus status, Exception ex) {
        return new ErrorResponseDto(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage()
        );
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseDto handleAuthenticationExceptions(Exception ex) {
        return createErrorResponse(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseDto handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return createErrorResponse(HttpStatus.CONFLICT, ex);
    }

}
