package com.smunity.api.domain.account.controller;

import com.smunity.api.domain.account.dto.InformationDto;
import com.smunity.api.domain.account.dto.UserDto;
import com.smunity.api.domain.account.service.EcampusService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/ecampus")
public class EcampusController {

    private final EcampusService ecampusService;

    public EcampusController(EcampusService ecampusService) {
        this.ecampusService = ecampusService;
    }

    @PostMapping()
    public InformationDto getInformation(@RequestBody UserDto signInDto) throws RuntimeException, IOException {
        Map<String, String> cookies = ecampusService.signIn(signInDto.getUsername(), signInDto.getPassword());
        InformationDto informationDto = ecampusService.getInformation(cookies);
        return informationDto;
    }
}
