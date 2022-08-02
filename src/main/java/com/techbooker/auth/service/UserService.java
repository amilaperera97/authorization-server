package com.techbooker.auth.service;

import com.techbooker.auth.dto.RoleDto;
import com.techbooker.auth.dto.UserDto;
import com.techbooker.auth.exception.cutom.DuplicateEntryException;
import com.techbooker.auth.model.Permission;
import com.techbooker.auth.model.Role;
import com.techbooker.auth.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface UserService {

    User saveUser(UserDto user, com.techbooker.auth.dto.Role role) throws RoleNotFoundException, DuplicateEntryException;

    Role saveRole(RoleDto role);

    void addRoleToUser(String email, String role) throws UsernameNotFoundException, RoleNotFoundException;

    User getUserByUsername(String email) throws UsernameNotFoundException;

    List<User> getAllUsers();

    void deleteUser(User user);

    void assignPermissionByRole(String role, List<Permission> permissionList) throws RoleNotFoundException;
}
