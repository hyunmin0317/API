package com.smunity.api.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
public class RestException extends RuntimeException {
    private HttpStatus httpStatus;
}
