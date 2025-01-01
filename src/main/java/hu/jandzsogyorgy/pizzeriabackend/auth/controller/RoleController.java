package hu.jandzsogyorgy.pizzeriabackend.auth.controller;

import hu.jandzsogyorgy.pizzeriabackend.auth.dto.RoleDto;
import hu.jandzsogyorgy.pizzeriabackend.auth.map.RoleMapper;
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
@RequestMapping("/api/admin/roles")
public class RoleController {
    private final UserRoleService userRoleService;
    private final RoleMapper roleMapper;

    @GetMapping
    public List<RoleDto> listRoles() {
        return roleMapper.toDto(userRoleService.listRoles());
    }
}
