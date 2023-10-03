package com.smunity.api.domain.account.controller;

import com.smunity.api.domain.account.dto.AuthDto;
import com.smunity.api.domain.account.dto.ResponseDto;
import com.smunity.api.domain.account.dto.SignInDto;
import com.smunity.api.domain.account.dto.SignUpDto;
import com.smunity.api.domain.account.service.AccountService;
import com.smunity.api.domain.account.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AuthService authService;

    public AccountController(AccountService accountService, AuthService authService) {
        this.accountService = accountService;
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDto> signUp(@RequestBody SignUpDto signUpDto) {
        ResponseDto responseDto = accountService.signUp(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDto> signIn(@RequestBody SignInDto signInDto) throws RuntimeException {
        ResponseDto responseDto = accountService.signIn(signInDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<AuthDto> authenticate(@RequestBody SignInDto signInDto) throws RuntimeException, IOException {
        Map<String, String> cookies = authService.signIn(signInDto);
        AuthDto authDto = authService.getInformation(cookies);
        return ResponseEntity.status(HttpStatus.OK).body(authDto);
    }
}
