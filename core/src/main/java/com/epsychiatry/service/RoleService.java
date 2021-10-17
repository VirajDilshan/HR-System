package com.epsychiatry.service;

import com.epsychiatry.model.management.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role saveRole(Role role);
    void deleteRoleById(Long id);
    List<Role> getAllRoles();
    Optional<Role> getRoleById(Long id);
}
