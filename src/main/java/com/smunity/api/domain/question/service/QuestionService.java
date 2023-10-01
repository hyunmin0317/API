package com.smunity.api.domain.question.service;

import com.smunity.api.domain.question.dto.QuestionDto;
import java.util.List;


public interface QuestionService {
    List<QuestionDto> getAllQuestions();
    QuestionDto createQuestion(QuestionDto questionDto, String token);
    QuestionDto getQuestionById(Long id);
    QuestionDto updateQuestion(Long id, QuestionDto questionDto, String token);
    void deleteQuestion(Long id, String token);
}
