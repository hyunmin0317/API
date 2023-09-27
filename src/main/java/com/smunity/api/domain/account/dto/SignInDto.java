package com.smunity.api.domain.account.dto;


import com.smunity.api.domain.account.domain.User;
import lombok.Data;

@Data
public class SignInDto {
    private String username;
    private String password;
}
