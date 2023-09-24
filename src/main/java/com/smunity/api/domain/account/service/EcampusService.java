package com.smunity.api.domain.account.service;

import com.smunity.api.domain.account.dto.InformationDto;

import java.io.IOException;
import java.util.Map;

public interface EcampusService {
    Map<String, String> signIn(String username, String password) throws IOException;
    InformationDto getInformation(Map<String, String> cookies) throws IOException;
}
