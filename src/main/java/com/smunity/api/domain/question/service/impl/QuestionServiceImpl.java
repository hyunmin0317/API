package com.smunity.api.domain.question.service.impl;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.question.entity.Question;
import com.smunity.api.domain.question.dto.QuestionDto;
import com.smunity.api.domain.question.repository.QuestionRepository;
import com.smunity.api.domain.question.service.QuestionService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.error.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Override
    public List<QuestionDto.Response> getAllQuestions() {
        List<Question> questionList = questionRepository.findAll();
        return QuestionDto.Response.of(questionList);
    }

    @Override
    public QuestionDto.Response createQuestion(QuestionDto.Request questionDto, String token) {
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.getByUsername(username).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        Question question = questionDto.toEntity(user);
        Question saveQuestion = questionRepository.save(question);
        return QuestionDto.Response.of(saveQuestion);
    }

    @Override
    public QuestionDto.Response getQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        return QuestionDto.Response.of(question);
    }

    @Override
    public QuestionDto.Response updateQuestion(Long id, QuestionDto.Request questionDto, String token) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(question.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        question.update(questionDto.getSubject(), questionDto.getContent(), questionDto.getAnonymous());
        Question changedQuestion = questionRepository.save(question);
        return QuestionDto.Response.of(changedQuestion);
    }

    @Override
    public void deleteQuestion(Long id, String token) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new RestException(HttpStatus.NO_CONTENT));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(question.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        questionRepository.delete(question);
    }
}