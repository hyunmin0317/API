package com.smunity.api.domain.qna.service;

import com.smunity.api.domain.qna.dto.QuestionDto;
import java.util.List;


public interface QuestionService {
    List<QuestionDto> findAllQuestions();
    QuestionDto getQuestion(Long id);
    QuestionDto createQuestion(QuestionDto questionDto, String token);
    QuestionDto changeQuestion(Long id, QuestionDto questionDto, String token);
    void deleteQuestion(Long id, String token);
}
