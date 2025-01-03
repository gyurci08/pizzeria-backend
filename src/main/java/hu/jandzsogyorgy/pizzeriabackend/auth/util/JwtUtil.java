package hu.jandzsogyorgy.pizzeriabackend.auth.util;

import hu.jandzsogyorgy.pizzeriabackend.config.JwtConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.AuthenticationException;
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

    private String getSecret(TokenType tokenType) {
        return tokenType == TokenType.ACCESS ? jwtConfig.getTokenSecret() : jwtConfig.getRefreshSecret();
    }

    @Scheduled(fixedRate = 3600000) // Run every hour
    private void removeExpiredTokensFromBlacklist() {
        Date now = new Date();
        blacklistedTokens.removeIf(token -> extractExpiration(TokenType.ACCESS, token).before(now));
        blacklistedTokens.removeIf(token -> extractExpiration(TokenType.REFRESH, token).before(now));
    }


    public String extractUsername(TokenType tokenType, String token) {
        return extractClaim(tokenType, token, Claims::getSubject);
    }

    public Date extractExpiration(TokenType tokenType, String token) {
        return extractClaim(tokenType, token, Claims::getExpiration);
    }

    public <T> T extractClaim(TokenType tokenType, String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims( tokenType, token);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClaims( TokenType tokenType, String token) {
        return Jwts.parser()
                .setSigningKey(getSecret(tokenType))
                .parseClaimsJws(token)
                .getBody();
    }

    public String createAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getTokenExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getTokenSecret())
                .compact();
    }
    public String createRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getRefreshExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getRefreshSecret())
                .compact();
    }
    public boolean validateToken(UserDetails userDetails,  TokenType tokenType, String token) {
        if (userDetails == null) {
            return false;
        }

        try {
            final String username = extractUsername( tokenType, token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(tokenType, token) && !isTokenBlacklisted(token));
        }
        catch (ExpiredJwtException | SignatureException e) {
            return false;
        }
    }

    public boolean isTokenExpired(TokenType tokenType, String token) {
        return extractExpiration(tokenType, token).before(new Date());
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

    public void invalidateToken(String token) {
        blacklistedTokens.add(token);
    }


}