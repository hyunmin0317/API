package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.dto.SignInResultDto;
import com.smunity.api.domain.account.dto.SignUpResultDto;


public interface AccountService {
    SignUpResultDto signUp(String username, String password, String email, boolean is_admin);

    SignInResultDto signIn(String username, String password) throws RuntimeException;
}
