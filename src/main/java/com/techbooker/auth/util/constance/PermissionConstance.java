package com.techbooker.auth.util.constance;

import com.techbooker.auth.dto.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.techbooker.auth.dto.Role.ROLE_ADMIN;
import static com.techbooker.auth.dto.Role.ROLE_USER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PermissionConstance {
    public static final String LOGIN = "login";
    public static final Map<Role, List<String>> PERMISSION_LIST_BY_ROLE = Map.ofEntries(
            Map.entry(ROLE_ADMIN, roleAdmin()),
            Map.entry(ROLE_USER, roleUser())
    );
    public static final List<String> PERMISSION_LIST = Arrays.asList(LOGIN);

    private static List<String> roleUser() {
        List<String> permissions = commonPermissions();
        return permissions;
    }

    private static List<String> roleAdmin() {
        List<String> permissions = commonPermissions();
        return permissions;
    }

    private static List<String> commonPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add(LOGIN);
        return permissions;
    }
}
