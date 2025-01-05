package hu.jandzsogyorgy.pizzeriabackend.config;

import hu.jandzsogyorgy.pizzeriabackend.exception.FilterExceptionHandler;
import hu.jandzsogyorgy.pizzeriabackend.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
//@EnableMethodSecurity // Not needed as I created seperated and readable function
@RequiredArgsConstructor
public class SecurityConfig {
    private final FilterExceptionHandler filterExceptionHandler;
    private final JwtRequestFilter jwtRequestFilter;
    private final CorsConfig corsConfig;


    private void configureRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth
                // Auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/auth/logout").authenticated()

                // Admin
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // Orders
                .requestMatchers("/api/orders/**").hasAnyRole("ADMIN", "CUSTOMER")

                // Menu-items
                .requestMatchers(HttpMethod.GET, "/api/menu-items/**").permitAll()
                .requestMatchers("/api/menu-items/**").hasRole("ADMIN")

                // Others
                .anyRequest().authenticated();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
                .authorizeHttpRequests(this::configureRequests)
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(filterExceptionHandler, WebAsyncManagerIntegrationFilter.class)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}