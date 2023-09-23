package com.smunity.api.domain.account.service.impl;

import com.smunity.api.domain.account.service.AccountService;
import com.smunity.api.global.common.CommonResponse;
import com.smunity.api.domain.account.dto.SignInResultDto;
import com.smunity.api.domain.account.dto.SignUpResultDto;
import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.global.config.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {
    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

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

    @Override
    public SignInResultDto signIn(String username, String password) throws RuntimeException {
        User user = userRepository.getByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUsername()), user.getRoles()))
                .build();
        setSuccessResult(signInResultDto);
        return signInResultDto;
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
