package com.smunity.api.domain.account.service.impl;

import com.smunity.api.domain.account.dto.UserDto;
import com.smunity.api.domain.account.entity.Department;
import com.smunity.api.domain.account.entity.Profile;
import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.account.entity.Year;
import com.smunity.api.domain.account.repository.DepartmentRepository;
import com.smunity.api.domain.account.repository.ProfileRepository;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.account.repository.YearRepository;
import com.smunity.api.domain.account.service.AccountService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.error.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final YearRepository yearRepository;
    private final DepartmentRepository departmentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto.Response signUp(UserDto.SignUp request) {
        Year year = yearRepository.findByYear(request.getUsername().substring(0, 4)).orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST));
        Department department = departmentRepository.findByName(request.getDepartment()).orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST));
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RestException(HttpStatus.CONFLICT);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = request.toEntity();
        User savedUser = userRepository.save(user);
        Profile profile = request.toEntity(savedUser, year, department);
        profileRepository.save(profile);
        String token = jwtTokenProvider.createToken(String.valueOf(savedUser.getUsername()), savedUser.getIs_superuser());
        return UserDto.Response.of(user.getUsername(), token);
    }

    @Override
    public UserDto.Response signIn(UserDto.SignIn request) throws RuntimeException {
        User user = userRepository.getByUsername(request.getUsername()).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        String token = jwtTokenProvider.createToken(String.valueOf(user.getUsername()), user.getIs_superuser());
        return UserDto.Response.of(user.getUsername(), token);
    }
}
