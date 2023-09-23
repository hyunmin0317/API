package com.smunity.api.service;

import com.smunity.api.data.dto.SignInResultDto;
import com.smunity.api.data.dto.SignUpResultDto;


public interface AccountService {
    SignUpResultDto signUp(String username, String password, String email, boolean is_admin);

    SignInResultDto signIn(String username, String password) throws RuntimeException;
}
