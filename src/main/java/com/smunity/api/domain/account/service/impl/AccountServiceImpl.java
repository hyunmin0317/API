package com.smunity.api.domain.account.service.impl;

import com.smunity.api.domain.account.dto.SignInDto;
import com.smunity.api.domain.account.dto.SignUpDto;
import com.smunity.api.domain.account.service.AccountService;
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
    public ResponseDto signUp(SignUpDto signUpDto) {
        boolean exists = userRepository.existsByUsername(signUpDto.getUsername());
        String token = null;
        if (!exists) {
            signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
            User user = signUpDto.toEntity();
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
    public ResponseDto signIn(SignInDto signInDto) throws RuntimeException {
        User user = userRepository.getByUsername(signInDto.getUsername());
        boolean success = passwordEncoder.matches(signInDto.getPassword(), user.getPassword());
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
