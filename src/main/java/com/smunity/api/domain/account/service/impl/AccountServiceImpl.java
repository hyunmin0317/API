package com.smunity.api.domain.account.service.impl;

import com.smunity.api.domain.account.entity.Department;
import com.smunity.api.domain.account.entity.Profile;
import com.smunity.api.domain.account.entity.Year;
import com.smunity.api.domain.account.dto.SignInDto;
import com.smunity.api.domain.account.dto.SignUpDto;
import com.smunity.api.domain.account.repository.DepartmentRepository;
import com.smunity.api.domain.account.repository.ProfileRepository;
import com.smunity.api.domain.account.repository.YearRepository;
import com.smunity.api.domain.account.service.AccountService;
import com.smunity.api.domain.account.dto.ResponseDto;
import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.error.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


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
    public ResponseDto signUp(SignUpDto signUpDto) {
        Optional<Year> year = yearRepository.findByYear(signUpDto.getUsername().substring(0,4));
        Optional<Department> department = departmentRepository.findByName(signUpDto.getDepartment());
        boolean check = userRepository.existsByUsername(signUpDto.getUsername()) || year.isEmpty() || department.isEmpty();
        if (check)
            throw new RestException(HttpStatus.BAD_REQUEST);

        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        User user = signUpDto.toUserEntity();
        User savedUser = userRepository.save(user);
        Profile profile = signUpDto.toProfileEntity(savedUser, year.get(), department.get());
        profileRepository.save(profile);
        String token  = jwtTokenProvider.createToken(String.valueOf(savedUser.getUsername()), savedUser.getIs_superuser());
        return ResponseDto.toDto(token);
    }

    @Override
    public ResponseDto signIn(SignInDto signInDto) throws RuntimeException {
        User user = userRepository.getByUsername(signInDto.getUsername());
        boolean matches = passwordEncoder.matches(signInDto.getPassword(), user.getPassword());
        if (!matches)
            throw new RestException(HttpStatus.UNAUTHORIZED);
        String token = jwtTokenProvider.createToken(String.valueOf(user.getUsername()), user.getIs_superuser());
        return ResponseDto.toDto(token);
    }
}
