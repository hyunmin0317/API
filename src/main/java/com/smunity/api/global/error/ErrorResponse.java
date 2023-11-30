package com.smunity.api.global.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private final int value;
    private final String reasonPhrase;

    @Builder
    public ErrorResponse(HttpStatus httpStatus) {
        this.value = httpStatus.value();
        this.reasonPhrase = httpStatus.getReasonPhrase();
    }
}