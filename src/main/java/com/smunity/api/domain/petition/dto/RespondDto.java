package com.smunity.api.domain.petition.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Respond;
import com.smunity.api.domain.petition.entity.Petition;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
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

    public RespondDto(Respond respond) {
        this.id = respond.getId();
        this.author_id = respond.getAuthor().getId();
        this.petition_id = respond.getPetition().getId();
        this.content = respond.getContent();
        this.create_date = respond.getCreate_date();
        this.modify_date = respond.getModify_date();
    }
}
