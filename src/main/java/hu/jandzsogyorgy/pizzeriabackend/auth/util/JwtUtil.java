package hu.jandzsogyorgy.pizzeriabackend.auth.util;

import hu.jandzsogyorgy.pizzeriabackend.config.JwtConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtConfig jwtConfig;
    private final Set<String> blacklistedTokens = Collections.synchronizedSet(new HashSet<>());




    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtConfig.getTokenSecret()).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getTokenExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getTokenSecret())
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (ExpiredJwtException e) {
            log.error("Token is expired");
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public void invalidateToken(String token) {
        blacklistedTokens.add(token);
    }

    @Scheduled(fixedRate = 3600000) // Run every hour
    public void removeExpiredTokensFromBlacklist() {
        Date now = new Date();
        blacklistedTokens.removeIf(token -> extractExpiration(token).before(now));
    }


    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getRefreshExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getRefreshSecret())
                .compact();
    }




}