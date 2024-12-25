package hu.jandzsogyorgy.pizzeriabackend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtConfig {
    private String  tokenSecret;
    private long    tokenExpiration;
    private String  refreshSecret;
    private long    refreshExpiration;
}
