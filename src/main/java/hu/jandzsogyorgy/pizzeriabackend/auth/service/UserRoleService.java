package hu.jandzsogyorgy.pizzeriabackend.auth.service;

import hu.jandzsogyorgy.pizzeriabackend.auth.entities.Role;
import hu.jandzsogyorgy.pizzeriabackend.auth.entities.User;
import hu.jandzsogyorgy.pizzeriabackend.auth.entities.UserRole;
import hu.jandzsogyorgy.pizzeriabackend.auth.repository.RoleRepository;
import hu.jandzsogyorgy.pizzeriabackend.auth.repository.UserRepository;
import hu.jandzsogyorgy.pizzeriabackend.auth.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public Long getUserId(String username) {
        return userRepository.findByUsername(username).getId();
    }


    public List<User> listUsers() {
        Map<Long, Role> roleMap = roleRepository.findAll().stream()
                .collect(
                        Collectors.toMap(
                                Role::getId,
                                Function.identity()
                        )
                );

        List<User> users = userRepository.findAll();
        Map<Long, List<Long>> userRoleMap = userRoleRepository.findAll().stream()
                .collect(
                        Collectors.groupingBy(
                                UserRole::getUserId,
                                Collectors.mapping(UserRole::getRoleId, Collectors.toList())
                        )
                );

        return users.stream()
                .peek(user -> {
                    List<Role> userRoles = userRoleMap.getOrDefault(user.getId(), Collections.emptyList())
                            .stream()
                            .map(roleMap::get)
                            .filter(Objects::nonNull)
                            .toList();
                    user.setRoles(userRoles);
                })
                .toList();
    }


    public User getUserWithRoles(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (user == null) {
            return null;
        }
        List<Long> roleIds = userRoleRepository.findAllByUserId(user.getId()).stream().map(UserRole::getRoleId).toList();
        user.setRoles(roleRepository.findAllById(roleIds));
        return user;
    }

    @Transactional
    public User registerUser(String email, String username, String password, String roleName) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email)) {
            throw new RuntimeException("Username or email already exists");
        }

        // Create new user
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        // Save user
        user = userRepository.save(user);

        // Assign CUSTOMER role
        Role role = roleRepository.findByName(roleName);

        if (role == null) {
            throw new RuntimeException("Role does not exists");
        }

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        userRoleRepository.save(userRole);

        // Set roles for the user object
        user.setRoles(List.of(role));

        return user;
    }


}
