package com.smunity.api.domain.account.controller;

import com.smunity.api.domain.account.dto.SignInResultDto;
import com.smunity.api.domain.account.dto.SignUpDto;
import com.smunity.api.domain.account.dto.SignUpResultDto;
import com.smunity.api.domain.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/register")
    public SignUpResultDto signUp(@RequestBody SignUpDto signUpDto) {
        SignUpResultDto signUpResultDto = accountService.signUp(signUpDto.getUsername(), signUpDto.getPassword(), signUpDto.getEmail(), signUpDto.getIs_admin());
        return signUpResultDto;
    }

    @PostMapping(value = "/login")
    public SignInResultDto signIn(@RequestBody SignUpDto signUpDto) throws RuntimeException {
        SignInResultDto signInResultDto = accountService.signIn(signUpDto.getUsername(), signUpDto.getPassword());
        return signInResultDto;
    }
}
