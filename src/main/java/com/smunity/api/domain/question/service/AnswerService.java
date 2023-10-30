package com.smunity.api.domain.question.service;

import com.smunity.api.domain.question.dto.AnswerDto;


public interface AnswerService {
    AnswerDto.Response createAnswer(Long questionId, AnswerDto.Request answerDto, String token);
    AnswerDto.Response getAnswerQuestionId(Long questionId);
    AnswerDto.Response updateAnswer(Long questionId, AnswerDto.Request answerDto, String token);
    void deleteAnswer(Long questionId, String token);
}
