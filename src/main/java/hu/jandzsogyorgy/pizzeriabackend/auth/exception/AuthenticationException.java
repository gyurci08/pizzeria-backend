package hu.jandzsogyorgy.pizzeriabackend.auth.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}

