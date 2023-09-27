package com.smunity.api.domain.account.controller;

import com.smunity.api.domain.account.dto.ResponseDto;
import com.smunity.api.domain.account.dto.SignInDto;
import com.smunity.api.domain.account.dto.SignUpDto;
import com.smunity.api.domain.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
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
}
