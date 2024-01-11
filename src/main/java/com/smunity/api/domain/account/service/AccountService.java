package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.dto.UserDto;

public interface AccountService {
    UserDto.Response signUp(UserDto.SignUp request);

    UserDto.Response signIn(UserDto.SignIn request);
}
