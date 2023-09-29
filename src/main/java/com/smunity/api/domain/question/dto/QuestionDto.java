package com.smunity.api.domain.question.dto;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.question.domain.Question;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
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

    public static QuestionDto toDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .subject(question.getSubject())
                .content(question.getContent())
                .anonymous(question.getAnonymous())
                .create_date(question.getCreate_date())
                .modify_date(question.getModify_date())
                .author_id(question.getAuthor().getId())
                .build();
    }
}