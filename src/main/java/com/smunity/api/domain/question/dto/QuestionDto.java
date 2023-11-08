package com.smunity.api.domain.question.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.question.entity.Question;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class QuestionDto {
    @Data
    @Builder
    @ApiModel(value = "QuestionDtoResponse")
    public static class Response {
        private Long id;
        private Long author_id;
        private String subject;
        private String content;
        private Boolean anonymous;
        private LocalDateTime create_date;
        private LocalDateTime modify_date;

        public static Response of(Question question) {
            return Response.builder()
                    .id(question.getId())
                    .author_id(question.getAuthor().getId())
                    .subject(question.getSubject())
                    .content(question.getContent())
                    .anonymous(question.getAnonymous())
                    .create_date(question.getCreateDate())
                    .modify_date(question.getModifyDate())
                    .build();
        }

        public static List<Response> of(List<Question> questionList) {
            return questionList.stream().map(Response::of).collect(Collectors.toList());
        }
    }

    @Data
    @ApiModel(value = "QuestionDtoRequest")
    public static class Request {
        private String subject;
        private String content;
        private Boolean anonymous;

        public Question toEntity(User user) {
            return Question.builder()
                    .subject(subject)
                    .content(content)
                    .anonymous(anonymous)
                    .author(user)
                    .build();
        }
    }
}
