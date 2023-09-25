package com.smunity.api.domain.account.dto;

import lombok.*;

@Data
@Builder
public class ResponseDto {
    private boolean success;
    private int code;
    private String msg;
    private String token;
}
