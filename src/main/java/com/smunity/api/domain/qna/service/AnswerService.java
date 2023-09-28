package com.smunity.api.domain.qna.service;

import com.smunity.api.domain.qna.dto.AnswerDto;
import com.smunity.api.domain.qna.dto.QuestionDto;

import java.util.List;


public interface AnswerService {
    List<AnswerDto> findAllAnswers();
    AnswerDto getAnswer(Long id);
    AnswerDto createAnswer(AnswerDto answerDto, String token);
    AnswerDto changeAnswer(Long id, AnswerDto answerDto, String token);
    void deleteAnswer(Long id, String token);
}
