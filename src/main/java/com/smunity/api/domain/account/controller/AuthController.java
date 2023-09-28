package com.smunity.api.domain.account.controller;

import com.smunity.api.domain.account.dto.InformationDto;
import com.smunity.api.domain.account.dto.SignInDto;
import com.smunity.api.domain.account.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping()
    public ResponseEntity<InformationDto> getInformation(@RequestBody SignInDto signInDto) throws RuntimeException, IOException {
        Map<String, String> cookies = authService.signIn(signInDto);
        InformationDto informationDto = authService.getInformation(cookies);
        return ResponseEntity.status(HttpStatus.OK).body(informationDto);
    }
}
