package hu.jandzsogyorgy.pizzeriabackend.auth.controller;

import hu.jandzsogyorgy.pizzeriabackend.auth.dto.UserDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.map.UserMapper;
import hu.jandzsogyorgy.pizzeriabackend.auth.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserRoleService userRoleService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> listUsers() {
        return userMapper.toDto(userRoleService.listUsers());
    }
}
