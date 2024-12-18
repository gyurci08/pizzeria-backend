package hu.jandzsogyorgy.pizzeriabackend.auth.service;

import hu.jandzsogyorgy.pizzeriabackend.auth.dto.AuthenticationRequestDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.dto.AuthenticationResponseDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.dto.LogoutRequestDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.dto.LogoutResponseDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.exception.AuthenticationException;
import hu.jandzsogyorgy.pizzeriabackend.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthenticationResponseDto login(AuthenticationRequestDto dto){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
            );
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.username());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponseDto(jwt);
    }

    public LogoutResponseDto logout(LogoutRequestDto dto, UserDetails userDetails) {
        // Check if the token is provided in the request
        if (dto.token() == null | userDetails == null) {
            return new LogoutResponseDto("Authentication and token is required");
        }

        try {
            // Validate the token against the authenticated user's details
            if (jwtUtil.validateToken(dto.token(), userDetails)) {
                jwtUtil.invalidateToken(dto.token());
                return new LogoutResponseDto("Logged out successfully");
            } else {
                return new LogoutResponseDto("Invalid token");
            }
        } catch (Exception e) {
            log.error("Error during logout", e);
            return new LogoutResponseDto("Error during logout: " + e.getMessage());
        }
    }
}
