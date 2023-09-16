package com.smunity.api.service;

import com.smunity.api.data.dto.SignUpResultDto;


public interface AccountService {
    SignUpResultDto signUp(String id, String password, String name, String role);
}
