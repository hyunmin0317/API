package com.smunity.api.domain.question.service;

import com.smunity.api.domain.question.dto.AnswerDto;


public interface AnswerService {
    AnswerDto createAnswer(Long questionId, AnswerDto answerDto, String token);
    AnswerDto getAnswerQuestionId(Long questionId);
    AnswerDto updateAnswer(Long questionId, AnswerDto answerDto, String token);
    void deleteAnswer(Long questionId, String token);
}
