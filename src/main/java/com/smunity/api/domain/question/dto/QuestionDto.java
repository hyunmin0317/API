package com.smunity.api.domain.question.dto;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.question.domain.Question;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    public static List<QuestionDto> toDtos(List<Question> questionList) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question: questionList)
            questionDtoList.add(toDto(question));
        return questionDtoList;
    }
}
