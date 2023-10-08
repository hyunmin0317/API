package com.smunity.api.domain.account.dto;

import com.smunity.api.global.common.CommonResponse;
import lombok.*;


@Data
public class ResponseDto {
    private boolean success;
    private int code;
    private String msg;
    private String token;

    public ResponseDto(boolean success, String token) {
        this.success = success;
        this.token = token;
        CommonResponse response = success ? CommonResponse.SUCCESS : CommonResponse.FAIL;
        this.code = response.getCode();
        this.msg = response.getMsg();
    }
}
