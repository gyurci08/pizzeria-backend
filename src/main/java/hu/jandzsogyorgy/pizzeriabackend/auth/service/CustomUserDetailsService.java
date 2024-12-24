package hu.jandzsogyorgy.pizzeriabackend.auth.service;

import hu.jandzsogyorgy.pizzeriabackend.auth.entities.User;
import hu.jandzsogyorgy.pizzeriabackend.auth.entities.UserRole;
import hu.jandzsogyorgy.pizzeriabackend.auth.repository.RoleRepository;
import hu.jandzsogyorgy.pizzeriabackend.auth.repository.UserRepository;
import hu.jandzsogyorgy.pizzeriabackend.auth.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRoleService.getUserWithRoles(username);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .collect(Collectors.toList())
        );

            log.debug("UserDetails: {}", userDetails);
            return userDetails;
    }
}