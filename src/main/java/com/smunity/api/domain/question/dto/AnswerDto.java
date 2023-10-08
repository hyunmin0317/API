package com.smunity.api.domain.question.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.question.entity.Answer;
import com.smunity.api.domain.question.entity.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class AnswerDto {
    private Long id;
    private Long author_id;
    private Long question_id;
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

    public AnswerDto(Answer answer) {
        this.id = answer.getId();
        this.author_id = answer.getAuthor().getId();
        this.question_id = answer.getQuestion().getId();
        this.content = answer.getContent();
        this.create_date = answer.getCreate_date();
        this.modify_date = answer.getModify_date();
    }
}
