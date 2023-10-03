package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.dto.AuthDto;
import com.smunity.api.domain.account.dto.SignInDto;
import java.io.IOException;
import java.util.Map;


public interface AuthService {
    Map<String, String> signIn(SignInDto signInDto) throws IOException;
    AuthDto getInformation(Map<String, String> cookies) throws IOException;
}
