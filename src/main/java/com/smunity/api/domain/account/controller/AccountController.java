package com.smunity.api.domain.account.controller;

import com.smunity.api.domain.account.dto.ResponseDto;
import com.smunity.api.domain.account.dto.UserDto;
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
    public ResponseEntity<ResponseDto> signUp(@RequestBody UserDto userDto) {
        ResponseDto responseDto = accountService.signUp(userDto);
        if (responseDto.getCode() == 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDto> signIn(@RequestBody UserDto signInDto) throws RuntimeException {
        ResponseDto responseDto = accountService.signIn(signInDto.getUsername(), signInDto.getPassword());
        if (responseDto.getCode() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }
}
