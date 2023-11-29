package com.smunity.api.domain.account.controller;

import com.smunity.api.domain.account.dto.*;
import com.smunity.api.domain.account.service.AccountService;
import com.smunity.api.domain.account.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserDto.Response> signUp(@RequestBody UserDto.SignUp request) {
        UserDto.Response response = accountService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto.Response> signIn(@RequestBody UserDto.SignIn request) throws RuntimeException {
        UserDto.Response response = accountService.signIn(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<AuthDto> authenticate(@RequestBody UserDto.SignIn request) throws RuntimeException, IOException {
        Map<String, String> cookies = authService.signIn(request);
        AuthDto authDto = authService.getInformation(cookies);
        return ResponseEntity.status(HttpStatus.OK).body(authDto);
    }
}
