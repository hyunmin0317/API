package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.dto.AuthDto;

import java.util.Map;

public interface AuthService {
    Map<String, String> signIn(AuthDto.Request request);

    AuthDto.Response getInformation(Map<String, String> cookies);
}
