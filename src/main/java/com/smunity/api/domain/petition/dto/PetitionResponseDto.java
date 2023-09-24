package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.account.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
public class PetitionResponseDto extends PetitionDto {
    private Long id;

    private Long author_id;

    @Builder(builderMethodName = "petitionResponseDtoBuilder")
    public PetitionResponseDto(Long id, String subject, String content, Integer category, boolean anonymous, LocalDateTime create_date, LocalDateTime end_date, LocalDateTime modify_date, Integer status, Long author_id) {
        super(subject, content, category, anonymous, create_date, end_date, modify_date, status);
        this.id = id;
        this.author_id = author_id;
    }
}
