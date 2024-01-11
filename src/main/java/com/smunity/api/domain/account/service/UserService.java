package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.global.error.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.getByUsername(username).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
    }
}
