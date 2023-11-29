package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.dto.AuthDto;
import com.smunity.api.domain.account.dto.UserDto;

import java.io.IOException;
import java.util.Map;

public interface AuthService {
    Map<String, String> signIn(UserDto.SignIn signInDto) throws IOException;
    AuthDto getInformation(Map<String, String> cookies) throws IOException;
}
