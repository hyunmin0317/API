package com.smunity.api.domain.account.dto;


import com.smunity.api.domain.account.domain.User;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private Boolean is_admin;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .is_superuser(is_admin)
                .is_staff(is_admin)
                .is_active(true)
                .build();
    }
}
