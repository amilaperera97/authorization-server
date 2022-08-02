package com.techbooker.auth.util.constance;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerConstance {
    public static final String API_V1 = "/api/v1";
    public static final String PUBLIC = "/public";

    public static final String ADMIN = "/admin";

    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";

    public static final String CHECK_TOKEN = "/check_token";
    public static final String REFRESH_TOKEN = "/token";
}
