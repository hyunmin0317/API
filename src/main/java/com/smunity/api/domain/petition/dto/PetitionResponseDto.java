package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.account.domain.User;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class PetitionResponseDto {
    private Long id;

    private String subject;

    private String content;

    private Integer category;

    private boolean anonymous;

    private LocalDateTime end_date;

    private Integer status;

    private User author;
}
