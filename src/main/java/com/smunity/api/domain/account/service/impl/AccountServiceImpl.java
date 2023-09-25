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
        boolean exists = userRepository.existsByUsername(userDto.getUsername());
        String token = null;
        if (!exists) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = userDto.toEntity();
            userRepository.save(user);
            token = jwtTokenProvider.createToken(String.valueOf(user.getUsername()), user.getRoles());
        }
        ResponseDto responseDto = ResponseDto.builder()
                .success(!exists)
                .token(token)
                .build();
        return responseDto;
    }

    @Override
    public ResponseDto signIn(String username, String password) throws RuntimeException {
        User user = userRepository.getByUsername(username);
        boolean success = passwordEncoder.matches(password, user.getPassword());
        String token = null;
        if (success) {
            token = jwtTokenProvider.createToken(String.valueOf(user.getUsername()), user.getRoles());
        }
        ResponseDto responseDto = ResponseDto.builder()
                .success(success)
                .token(token)
                .build();
        return responseDto;
    }
}
