package com.smunity.api.domain.account.controller;

import com.smunity.api.domain.account.dto.ResponseDto;
import com.smunity.api.domain.account.dto.UserDto;
import com.smunity.api.domain.account.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/register")
    public ResponseDto signUp(@RequestBody UserDto userDto) {
        ResponseDto signUpResultDto = accountService.signUp(userDto);
        return signUpResultDto;
    }

    @PostMapping(value = "/login")
    public ResponseDto signIn(@RequestBody UserDto signInDto) throws RuntimeException {
        ResponseDto responseDto = accountService.signIn(signInDto.getUsername(), signInDto.getPassword());
        return responseDto;
    }
}
