package com.smunity.api.service.impl;

import com.smunity.api.common.CommonResponse;
import com.smunity.api.data.dto.SignInResultDto;
import com.smunity.api.data.dto.SignUpResultDto;
import com.smunity.api.data.entity.User;
import com.smunity.api.data.repository.UserRepository;
import com.smunity.api.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;


public class AccountServiceImpl implements AccountService {
    public UserRepository userRepository;
    public PasswordEncoder passwordEncoder;

    @Override
    public SignUpResultDto signUp(String username, String password, String email, boolean is_admin) {
        User user;
        user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .is_superuser(is_admin)
                .is_staff(is_admin)
                .is_active(true)
                .build();

        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignUpResultDto();

        if (!savedUser.getUsername().isEmpty()) {
            setSuccessResult(signUpResultDto);
        } else {
            setFailResult(signUpResultDto);
        }
        return signUpResultDto;
    }

    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
