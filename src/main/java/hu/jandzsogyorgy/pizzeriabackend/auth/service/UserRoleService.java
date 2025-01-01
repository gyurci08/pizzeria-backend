package hu.jandzsogyorgy.pizzeriabackend.auth.service;

import hu.jandzsogyorgy.pizzeriabackend.auth.entities.User;
import hu.jandzsogyorgy.pizzeriabackend.auth.entities.UserRole;
import hu.jandzsogyorgy.pizzeriabackend.auth.repository.RoleRepository;
import hu.jandzsogyorgy.pizzeriabackend.auth.repository.UserRepository;
import hu.jandzsogyorgy.pizzeriabackend.auth.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public User getUserWithRoles(String usernameOrEmail) {
        User user = userRepository.findByUsername(usernameOrEmail);
        if (user == null) {
            user = userRepository.findByEmail(usernameOrEmail);
        }
        if (user == null) {
            return null;
        }
        List<Long> roleIds = userRoleRepository.findAllByUserId(user.getId()).stream().map(UserRole::getRoleId).toList();
        user.setRoles(roleRepository.findAllById(roleIds));
        return user;
    }
}
