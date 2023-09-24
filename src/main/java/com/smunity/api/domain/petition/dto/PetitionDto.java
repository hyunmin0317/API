package com.smunity.api.domain.petition.dto;

import java.time.LocalDateTime;
import com.smunity.api.domain.account.domain.User;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PetitionDto {
    private String subject;

    private String content;

    private Integer category;

    private boolean anonymous;

    private LocalDateTime create_date;

    private LocalDateTime end_date;

    private LocalDateTime modify_date;

    private Integer status;

    private Long author_id;
}
