package com.smunity.api.controller;

import com.smunity.api.data.dto.SignUpDto;
import com.smunity.api.data.dto.SignUpResultDto;
import com.smunity.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
