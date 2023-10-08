package com.smunity.api.domain.question.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.question.entity.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class QuestionDto {
    private Long id;
    private Long author_id;
    private String subject;
    private String content;
    private Boolean anonymous;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;

    public Question toEntity(User user) {
        return Question.builder()
                .subject(subject)
                .content(content)
                .anonymous(anonymous)
                .author(user)
                .build();
    }

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.author_id = question.getAuthor().getId();
        this.subject = question.getSubject();
        this.content = question.getContent();
        this.anonymous = question.getAnonymous();
        this.create_date = question.getCreate_date();
        this.modify_date = question.getModify_date();
    }
}
