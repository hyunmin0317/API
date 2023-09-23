package com.smunity.api.domain.account.service;

public interface EcampusService {
    String signIn(String username, String password) throws RuntimeException;
    void getInformation(String username, String password);
}
