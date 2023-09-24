package com.smunity.api.domain.account.service;

import java.io.IOException;
import java.util.Map;

public interface EcampusService {
    Map<String, String> signIn(String username, String password) throws RuntimeException, IOException;
    void getInformation(String username, String password);
}
