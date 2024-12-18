package hu.jandzsogyorgy.pizzeriabackend.auth.controller;

import hu.jandzsogyorgy.pizzeriabackend.auth.dto.LogoutRequestDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.dto.LogoutResponseDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.exception.AuthenticationException;
import hu.jandzsogyorgy.pizzeriabackend.auth.service.CustomUserDetailsService;
import hu.jandzsogyorgy.pizzeriabackend.auth.util.JwtUtil;
import hu.jandzsogyorgy.pizzeriabackend.auth.dto.AuthenticationRequestDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.dto.AuthenticationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthenticationResponseDto createAuthenticationToken(@RequestBody AuthenticationRequestDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponseDto(jwt);
    }



    @PostMapping("/logout")
    public LogoutResponseDto logout(@RequestBody LogoutRequestDto logoutRequest,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        log.info("Logout request received");

        // Check if the token is provided in the request
        if (logoutRequest.token() == null) {
            return new LogoutResponseDto("Token is required");
        }

        try {
            // Validate the token against the authenticated user's details
            if (jwtUtil.validateToken(logoutRequest.token(), userDetails)) {
                // Invalidate the token (e.g., add it to a blacklist or mark it as expired)
                jwtUtil.invalidateToken(logoutRequest.token());
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
