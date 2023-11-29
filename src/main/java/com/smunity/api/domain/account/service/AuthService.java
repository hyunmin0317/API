package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.dto.AuthDto;

import java.io.IOException;
import java.util.Map;

public interface AuthService {
    Map<String, String> signIn(AuthDto.Request request) throws IOException;
    AuthDto.Response getInformation(Map<String, String> cookies) throws IOException;
}
