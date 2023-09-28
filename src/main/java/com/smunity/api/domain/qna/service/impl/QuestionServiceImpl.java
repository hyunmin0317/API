package com.smunity.api.domain.qna.service.impl;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.domain.Petition;
import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.qna.domain.Question;
import com.smunity.api.domain.qna.dto.QuestionDto;
import com.smunity.api.domain.qna.repository.QuestionRepository;
import com.smunity.api.domain.qna.service.QuestionService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService {
    public JwtTokenProvider jwtTokenProvider;
    public UserRepository userRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, QuestionRepository questionRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionDto> findAllQuestions() {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Question> questionList = questionRepository.findAll();
        for (Question question: questionList) {
            QuestionDto questionDto = QuestionDto.toDto(question);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    @Override
    public QuestionDto getQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        return QuestionDto.toDto(question);
    }

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto, String token) {
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.getByUsername(username);
        Question question = questionDto.toEntity(user);
        Question saveQuestion = questionRepository.save(question);
        QuestionDto questionResponseDto = QuestionDto.toDto(saveQuestion);
        return questionResponseDto;
    }

    @Override
    public QuestionDto changeQuestion(Long id, QuestionDto questionDto, String token) {
        return null;
    }

    @Override
    public void deleteQuestion(Long id, String token) {

    }
}
