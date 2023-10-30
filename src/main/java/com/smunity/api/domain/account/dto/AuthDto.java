package com.smunity.api.domain.account.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AuthDto {
    private String username;
    private String department;
    private String email;

    public static AuthDto of(String username, String department, String email) {
        return AuthDto.builder()
                .username(username)
                .department(department)
                .email(email)
                .build();
    }
}
