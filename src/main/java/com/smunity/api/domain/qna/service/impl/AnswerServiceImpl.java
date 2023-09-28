package com.smunity.api.domain.qna.service.impl;

import com.smunity.api.domain.qna.dto.AnswerDto;
import com.smunity.api.domain.qna.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Override
    public List<AnswerDto> findAllAnswers() {
        return null;
    }

    @Override
    public AnswerDto getAnswer(Long id) {
        return null;
    }

    @Override
    public AnswerDto createAnswer(AnswerDto answerDto, String token) {
        return null;
    }

    @Override
    public AnswerDto changeAnswer(Long id, AnswerDto answerDto, String token) {
        return null;
    }

    @Override
    public void deleteAnswer(Long id, String token) {

    }
}
