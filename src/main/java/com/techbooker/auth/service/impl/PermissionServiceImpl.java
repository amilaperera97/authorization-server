package com.techbooker.auth.service.impl;

import com.techbooker.auth.model.Permission;
import com.techbooker.auth.repository.PermissionRepository;
import com.techbooker.auth.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public void saveAll(List<String> permissions) {
        List<Permission> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            permissionList.add(new Permission(permission));
        }
        permissionRepository.saveAll(permissionList);
    }
}
