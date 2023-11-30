package com.smunity.api.domain.question.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.question.entity.Answer;
import com.smunity.api.domain.question.entity.Question;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class AnswerDto {
    @Data
    @Builder
    @ApiModel(value = "AnswerDtoResponse")
    public static class Response {
        private Long id;
        private Long author_id;
        private Long question_id;
        private String content;
        private LocalDateTime create_date;
        private LocalDateTime modify_date;

        public static Response of(Answer answer) {
            return Response.builder()
                    .id(answer.getId())
                    .author_id(answer.getAuthor().getId())
                    .question_id(answer.getQuestion().getId())
                    .content(answer.getContent())
                    .create_date(answer.getCreateDate())
                    .modify_date(answer.getModifyDate())
                    .build();
        }
    }

    @Data
    @Builder
    @ApiModel(value = "AnswerDtoRequest")
    public static class Request {
        private String content;

        public Answer toEntity(User user, Question question) {
            return Answer.builder()
                    .content(content)
                    .author(user)
                    .question(question)
                    .build();
        }
    }
}