package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.dto.ResponseDto;
import com.smunity.api.domain.account.dto.UserDto;


public interface AccountService {
    ResponseDto signUp(UserDto userDto);

    ResponseDto signIn(String username, String password) throws RuntimeException;
}
