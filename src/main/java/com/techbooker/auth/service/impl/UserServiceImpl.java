package com.techbooker.auth.service.impl;

import com.techbooker.auth.dto.RoleDto;
import com.techbooker.auth.dto.UserDto;
import com.techbooker.auth.exception.cutom.DuplicateEntryException;
import com.techbooker.auth.model.AuthUserDetail;
import com.techbooker.auth.model.Permission;
import com.techbooker.auth.model.Role;
import com.techbooker.auth.model.User;
import com.techbooker.auth.repository.RoleRepository;
import com.techbooker.auth.repository.UserRepository;
import com.techbooker.auth.service.UserService;
import com.techbooker.auth.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EntityConverter entityConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(UserDto user, com.techbooker.auth.dto.Role role) throws RoleNotFoundException, DuplicateEntryException {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateEntryException("User already exists");
        }

        User userInfo = entityConverter.convert(user, User.class);
        userInfo.setEmail(user.getUsername());

        //TODO remove permission
        enableUser(userInfo);

        userInfo.setRoles(new ArrayList<>());

        Optional<Role> optionalRole = roleRepository.findRoleByName(role.name());
        optionalRole.orElseThrow(() -> new RoleNotFoundException("Invalid role name!"));
        userInfo.getRoles().add(optionalRole.get());
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userRepository.save(userInfo);
    }

    private void enableUser(User user) {
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
    }

    @Override
    public Role saveRole(RoleDto role) {
        Role roleInfo = entityConverter.convert(role, Role.class);
        return roleRepository.save(roleInfo);
    }

    @Override
    public void addRoleToUser(String email, String role) throws UsernameNotFoundException, RoleNotFoundException {
        Optional<Role> optionalRole = roleRepository.findRoleByName(role);
        optionalRole.orElseThrow(() -> new RoleNotFoundException("Invalid role name!"));

        getUserByEmail(email).getRoles().add(optionalRole.get());
    }

    @Override
    public User getUserByUsername(String email) throws UsernameNotFoundException {
        return getUserByEmail(email);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void assignPermissionByRole(String role, List<Permission> permissionList) throws RoleNotFoundException {
        Optional<Role> optionalRole = roleRepository.findRoleByName(role);
        optionalRole.orElseThrow(() -> new RoleNotFoundException("Invalid role!"));

        optionalRole.get().setPermissions(permissionList);
        roleRepository.saveAndFlush(optionalRole.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AuthUserDetail(getUserByEmail(username));
    }

    @NotNull
    private User getUserByEmail(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(email);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username is not found or wrong!"));
        return optionalUser.get();
    }
}
