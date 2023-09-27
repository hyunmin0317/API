package com.smunity.api.domain.account.service.impl;

import com.smunity.api.domain.account.domain.Department;
import com.smunity.api.domain.account.domain.Profile;
import com.smunity.api.domain.account.domain.Year;
import com.smunity.api.domain.account.dto.SignInDto;
import com.smunity.api.domain.account.dto.SignUpDto;
import com.smunity.api.domain.account.repository.DepartmentRepository;
import com.smunity.api.domain.account.repository.ProfileRepository;
import com.smunity.api.domain.account.repository.YearRepository;
import com.smunity.api.domain.account.service.AccountService;
import com.smunity.api.domain.account.dto.ResponseDto;
import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {
    public UserRepository userRepository;
    public ProfileRepository profileRepository;
    public YearRepository yearRepository;
    public DepartmentRepository departmentRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, YearRepository yearRepository, DepartmentRepository departmentRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.yearRepository = yearRepository;
        this.departmentRepository = departmentRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseDto signUp(SignUpDto signUpDto) {
        Optional<Year> year = yearRepository.findByYear(signUpDto.getUsername().substring(0,4));
        Optional<Department> department = departmentRepository.findByName(signUpDto.getDepartment());
        boolean check = userRepository.existsByUsername(signUpDto.getUsername()) || year.isEmpty() || department.isEmpty();
        if (check)
            throw new CustomException(HttpStatus.BAD_REQUEST);

        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        User user = signUpDto.toUserEntity();
        User savedUser = userRepository.save(user);
        Profile profile = signUpDto.toProfileEntity(savedUser, year.get(), department.get());
        profileRepository.save(profile);
        String token  = jwtTokenProvider.createToken(String.valueOf(savedUser.getUsername()), savedUser.getRoles());
        ResponseDto responseDto = ResponseDto.builder()
                .success(true)
                .token(token)
                .build();
        return responseDto;
    }

    @Override
    public ResponseDto signIn(SignInDto signInDto) throws RuntimeException {
        User user = userRepository.getByUsername(signInDto.getUsername());
        boolean matches = passwordEncoder.matches(signInDto.getPassword(), user.getPassword());
        if (!matches)
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        String token = jwtTokenProvider.createToken(String.valueOf(user.getUsername()), user.getRoles());
        ResponseDto responseDto = ResponseDto.builder()
                .success(true)
                .token(token)
                .build();
        return responseDto;
    }
}
