package com.smunity.api.domain.account.dto;

import com.smunity.api.global.common.CommonResponse;
import lombok.*;


@Data
@Builder
public class ResponseDto {
    private boolean success;
    private int code;
    private String msg;
    private String token;

    public static ResponseDto of(boolean success, String token) {
        CommonResponse response = success ? CommonResponse.SUCCESS : CommonResponse.FAIL;
        return ResponseDto.builder()
                .success(success)
                .token(token)
                .code(response.getCode())
                .msg(response.getMsg())
                .build();
    }
}
