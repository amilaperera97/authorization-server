package com.techbooker.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OauthClientDetailDto {
    private String id;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
}
