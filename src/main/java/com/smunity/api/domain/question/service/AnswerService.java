package com.smunity.api.domain.question.service;

import com.smunity.api.domain.question.dto.AnswerDto;

import java.util.List;


public interface AnswerService {
    List<AnswerDto> findAllAnswers();
    AnswerDto getAnswer(Long id);
    AnswerDto createAnswer(AnswerDto answerDto, String token);
    AnswerDto changeAnswer(Long id, AnswerDto answerDto, String token);
    void deleteAnswer(Long id, String token);
}
