package com.smunity.api.domain.account.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class InformationDto {
    private String username;
    private String department;
    private String email;
}
