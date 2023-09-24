package com.smunity.api.domain.petition.dto;

import java.time.LocalDateTime;
import com.smunity.api.domain.account.domain.User;
import lombok.Data;


@Data
public class PetitionDto {
    private String subject;

    private String content;

    private Integer category;

    private boolean anonymous;

    private LocalDateTime end_date;

    private Integer status;

    private User author;
}
