package com.smunity.api.domain.petition.dto;

import java.time.LocalDateTime;
import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.petition.entity.Petition;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PetitionDto {
    private Long id;
    private Long author_id;
    private String subject;
    private String content;
    private Integer category;
    private Boolean anonymous;
    private LocalDateTime create_date;
    private LocalDateTime end_date;
    private LocalDateTime modify_date;
    private Integer status;

    public Petition toEntity(User user) {
        return Petition.builder()
                .subject(subject)
                .content(content)
                .category(category)
                .anonymous(anonymous)
                .end_date(end_date)
                .status(status)
                .author(user)
                .build();
    }

    public PetitionDto(Petition petition) {
        this.id = petition.getId();
        this.author_id = petition.getAuthor().getId();
        this.subject = petition.getSubject();
        this.content = petition.getContent();
        this.category = petition.getCategory();
        this.anonymous = petition.getAnonymous();
        this.create_date = petition.getCreate_date();
        this.end_date = petition.getEnd_date();
        this.modify_date = petition.getModify_date();
        this.status = petition.getStatus();
    }
}
