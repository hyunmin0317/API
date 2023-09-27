package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.dto.ResponseDto;
import com.smunity.api.domain.account.dto.SignInDto;
import com.smunity.api.domain.account.dto.SignUpDto;


public interface AccountService {
    ResponseDto signUp(SignUpDto signUpDto);

    ResponseDto signIn(SignInDto signInDto) throws RuntimeException;
}
