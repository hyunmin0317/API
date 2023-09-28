package com.smunity.api.domain.qna.dto;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.qna.domain.Answer;
import com.smunity.api.domain.qna.domain.Question;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Builder
public class AnswerDto {
    private Long id;

    private Long author_id;

    private Long petition_id;

    private String content;

    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    public Answer toEntity(User user, Question question) {
        return Answer.builder()
                .content(content)
                .author(user)
                .question(question)
                .build();
    }

    public static AnswerDto toDto(Answer answer) {
        return AnswerDto.builder()
                .id(answer.getId())
                .author_id(answer.getAuthor().getId())
                .petition_id(answer.getQuestion().getId())
                .content(answer.getContent())
                .create_date(answer.getCreate_date())
                .modify_date(answer.getModify_date())
                .build();
    }
}
