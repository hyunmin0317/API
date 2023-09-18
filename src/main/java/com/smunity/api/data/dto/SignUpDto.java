package com.smunity.api.data.dto;


import lombok.Data;

@Data
public class SignUpDto {
    private String username;
    private String password;
    private String email;
    private Boolean is_admin;
}
