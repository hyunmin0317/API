package com.smunity.api.domain.question.service.impl;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.question.domain.Question;
import com.smunity.api.domain.question.dto.QuestionDto;
import com.smunity.api.domain.question.repository.QuestionRepository;
import com.smunity.api.domain.question.service.QuestionService;
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
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(question.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new CustomException(HttpStatus.FORBIDDEN);
        question.setSubject(questionDto.getSubject());
        question.setContent(questionDto.getContent());
        question.setAnonymous(questionDto.getAnonymous());
        Question changedQuestion = questionRepository.save(question);
        return QuestionDto.toDto(changedQuestion);
    }

    @Override
    public void deleteQuestion(Long id, String token) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NO_CONTENT));
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(question.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new CustomException(HttpStatus.FORBIDDEN);
        questionRepository.delete(question);
    }
}
