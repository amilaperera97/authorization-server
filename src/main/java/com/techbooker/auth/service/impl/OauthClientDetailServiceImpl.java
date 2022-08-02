package com.techbooker.auth.service.impl;

import com.techbooker.auth.dto.OauthClientDetailDto;
import com.techbooker.auth.model.OauthClientDetail;
import com.techbooker.auth.repository.OauthClientDetailRepository;
import com.techbooker.auth.service.OauthClientDetailService;
import com.techbooker.auth.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthClientDetailServiceImpl implements OauthClientDetailService {
    private final EntityConverter entityConverter;
    private final OauthClientDetailRepository oauthClientDetailRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OauthClientDetailDto save(OauthClientDetailDto oauthClientDetail) {
        OauthClientDetail oauthClient = entityConverter.convert(oauthClientDetail, OauthClientDetail.class);
        oauthClient.setClientSecret(passwordEncoder.encode(oauthClient.getClientSecret()));

        oauthClient = oauthClientDetailRepository.save(oauthClient);
        return entityConverter.convert(oauthClient, OauthClientDetailDto.class);
    }
}
