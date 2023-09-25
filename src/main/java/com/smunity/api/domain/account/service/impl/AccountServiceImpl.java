package com.smunity.api.domain.account.service.impl;

import com.smunity.api.domain.account.dto.UserDto;
import com.smunity.api.domain.account.service.AccountService;
import com.smunity.api.global.common.CommonResponse;
import com.smunity.api.domain.account.dto.ResponseDto;
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
    public ResponseDto signUp(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userDto.toEntity();
        User savedUser = userRepository.save(user);

        ResponseDto responseDto = ResponseDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUsername()), user.getRoles()))
                .build();
        setResult(responseDto, !savedUser.getUsername().isEmpty());
        return responseDto;
    }

    @Override
    public ResponseDto signIn(String username, String password) throws RuntimeException {
        User user = userRepository.getByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }

        ResponseDto responseDto = ResponseDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUsername()), user.getRoles()))
                .build();
        setResult(responseDto, true);
        return responseDto;
    }

    private void setResult(ResponseDto result, boolean success) {
        result.setSuccess(success);
        if (success) {
            result.setCode(CommonResponse.SUCCESS.getCode());
            result.setMsg(CommonResponse.SUCCESS.getMsg());
        } else {
            result.setCode(CommonResponse.FAIL.getCode());
            result.setMsg(CommonResponse.FAIL.getMsg());
        }
    }
}
