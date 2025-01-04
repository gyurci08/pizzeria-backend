package hu.jandzsogyorgy.pizzeriabackend.filter;

import hu.jandzsogyorgy.pizzeriabackend.auth.exception.AuthenticationException;
import hu.jandzsogyorgy.pizzeriabackend.auth.service.CustomUserDetailsService;
import hu.jandzsogyorgy.pizzeriabackend.auth.util.JwtUtil;
import hu.jandzsogyorgy.pizzeriabackend.auth.util.TokenType;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final List<String> allowedPaths = List.of("/api/menu-items");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (allowedPaths.contains(request.getServletPath())) {
            chain.doFilter(request, response);
            return;
        }

        String token = extractToken(request);
        if (token != null) {
            processToken(token, request);
        }

        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private void processToken(String token, HttpServletRequest request) {
        try {
            String username = jwtUtil.extractUsername(TokenType.ACCESS, token);
            authenticateUser(username, token, request);
        } catch (ExpiredJwtException e) {
            handleExpiredToken(request, e);
        } catch (SignatureException e) {
            throw new AuthenticationException("Invalid token signature");
        }
    }

    private void authenticateUser(String username, String token, HttpServletRequest request) {
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            validateAndSetAuthentication(userDetails, token, request);
        }
    }

    private void validateAndSetAuthentication(UserDetails userDetails, String token, HttpServletRequest request) {
        if (jwtUtil.validateToken(userDetails, TokenType.ACCESS, token)) {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }

    private void handleExpiredToken(HttpServletRequest request, ExpiredJwtException e) {
        if (request.getServletPath().equals("/api/auth/refresh")) {
            log.info("Attempting token renewal");
        } else {
            throw new AuthenticationException("Access token has expired");
        }
    }
}



