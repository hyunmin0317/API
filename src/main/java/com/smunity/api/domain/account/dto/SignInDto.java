package com.smunity.api.domain.account.dto;


import lombok.Data;

@Data
public class SignInDto {
    private String username;
    private String password;
}
