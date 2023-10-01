package com.smunity.api.domain.question.service;

import com.smunity.api.domain.question.dto.AnswerDto;

import java.util.List;


public interface AnswerService {
    AnswerDto getAnswer(Long questionId);
    AnswerDto createAnswer(Long questionId, AnswerDto answerDto, String token);
    AnswerDto changeAnswer(Long questionId, AnswerDto answerDto, String token);
    void deleteAnswer(Long questionId, String token);
}
