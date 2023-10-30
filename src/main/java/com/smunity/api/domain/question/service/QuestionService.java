package com.smunity.api.domain.question.service;

import com.smunity.api.domain.question.dto.QuestionDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface QuestionService {
    List<QuestionDto.Response> getAllQuestions();
    QuestionDto.Response createQuestion(QuestionDto.Request questionDto, String token);
    QuestionDto.Response getQuestionById(Long id);
    @Transactional
    QuestionDto.Response updateQuestion(Long id, QuestionDto.Request questionDto, String token);
    void deleteQuestion(Long id, String token);
}
