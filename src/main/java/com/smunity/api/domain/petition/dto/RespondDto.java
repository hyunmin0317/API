package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.petition.entity.Respond;
import com.smunity.api.domain.petition.entity.Petition;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Builder
public class RespondDto {
    private Long id;
    private Long author_id;
    private Long petition_id;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;

    public Respond toEntity(User user, Petition petition) {
        return Respond.builder()
                .content(content)
                .author(user)
                .petition(petition)
                .build();
    }

    public static RespondDto toDto(Respond respond) {
        return RespondDto.builder()
                .id(respond.getId())
                .author_id(respond.getAuthor().getId())
                .petition_id(respond.getPetition().getId())
                .content(respond.getContent())
                .create_date(respond.getCreate_date())
                .modify_date(respond.getModify_date())
                .build();
    }
}
