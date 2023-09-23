package com.smunity.api.domain.account.controller;

import com.smunity.api.domain.account.dto.SignInDto;
import com.smunity.api.domain.account.service.EcampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ecampus")
public class EcampusController {

    private final EcampusService ecampusService;

    public EcampusController(EcampusService ecampusService) {
        this.ecampusService = ecampusService;
    }

    @PostMapping(value = "/login")
    public String signIn(@RequestBody SignInDto signInDto) throws RuntimeException {
        String result = ecampusService.signIn(signInDto.getUsername(), signInDto.getPassword());
        return result;
    }
}
