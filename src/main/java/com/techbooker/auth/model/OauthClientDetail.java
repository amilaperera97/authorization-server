package com.techbooker.auth.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details")
@Data
public class OauthClientDetail {
    @Id
    @Column(name = "client_id", nullable = false, length = 256)
    private String id;

    @Column(name = "resource_ids", length = 256)
    private String resourceIds;

    @Column(name = "client_secret", length = 256)
    private String clientSecret;

    @Column(name = "scope", length = 256)
    private String scope;

    @Column(name = "authorized_grant_types", length = 256)
    private String authorizedGrantTypes;

    @Column(name = "web_server_redirect_uri", length = 256)
    private String webServerRedirectUri;

    @Column(name = "authorities", length = 256)
    private String authorities;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(name = "additional_information", length = 4096)
    private String additionalInformation;

    @Column(name = "autoapprove", length = 256)
    private String autoapprove;

}