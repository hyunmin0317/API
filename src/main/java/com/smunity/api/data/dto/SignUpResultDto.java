package com.smunity.api.data.dto;

import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpResultDto {
    private boolean success;
    private int code;
    private String msg;
}
