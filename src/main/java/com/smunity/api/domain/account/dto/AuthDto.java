package com.smunity.api.domain.account.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

public class AuthDto {
    @Data
    @Builder
    @ApiModel(value = "AuthDtoResponse")
    public static class Response {
        private String username;
        private String department;
        private String email;

        public static Response of(String username, String department, String email) {
            return Response.builder()
                    .username(username)
                    .department(department)
                    .email(email)
                    .build();
        }
    }

    @Data
    @ApiModel(value = "AuthDtoRequest")
    public static class Request {
        private String username;
        private String password;
    }
}
