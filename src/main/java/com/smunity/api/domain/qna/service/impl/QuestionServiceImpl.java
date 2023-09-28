package com.smunity.api.domain.qna.service.impl;

import com.smunity.api.domain.qna.dto.QuestionDto;
import com.smunity.api.domain.qna.service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public List<QuestionDto> findAllQuestions() {
        return null;
    }

    @Override
    public QuestionDto getQuestion(Long id) {
        return null;
    }

    @Override
    public QuestionDto createQuestion(QuestionDto petitionDto, String token) {
        return null;
    }

    @Override
    public QuestionDto changeQuestion(Long id, QuestionDto petitionDto, String token) {
        return null;
    }

    @Override
    public void deleteQuestion(Long id, String token) {

    }
}
