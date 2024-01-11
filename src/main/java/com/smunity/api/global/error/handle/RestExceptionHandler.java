package com.smunity.api.global.error.handle;

import com.smunity.api.global.error.ErrorResponse;
import com.smunity.api.global.error.exception.RestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleException(RestException ex) {
        HttpStatus httpStatus = ex.getHttpStatus();
        ErrorResponse errorResponse = ErrorResponse.builder().httpStatus(httpStatus).build();
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
