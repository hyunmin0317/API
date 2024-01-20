package com.smunity.api.domain.question.controller;

import com.smunity.api.domain.question.dto.QuestionDto;
import com.smunity.api.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping()
    ResponseEntity<List<QuestionDto.Response>> getAllQuestions() {
        List<QuestionDto.Response> questionDtoList = questionService.getAllQuestions();
        return ResponseEntity.status(HttpStatus.OK).body(questionDtoList);
    }

    @PostMapping()
    public ResponseEntity<QuestionDto.Response> createQuestion(@RequestBody QuestionDto.Request questionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        QuestionDto.Response question = questionService.createQuestion(questionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping(value = "/{questionId}")
    ResponseEntity<QuestionDto.Response> getQuestionById(@PathVariable Long questionId) {
        QuestionDto.Response petitionDto = questionService.getQuestionById(questionId);
        return ResponseEntity.status(HttpStatus.OK).body(petitionDto);
    }

    @PutMapping(value = "/{questionId}")
    ResponseEntity<QuestionDto.Response> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionDto.Request questionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        QuestionDto.Response petition = questionService.updateQuestion(questionId, questionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @DeleteMapping(value = "/{questionId}")
    ResponseEntity<?> deleteQuestion(@PathVariable Long questionId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        questionService.deleteQuestion(questionId, token);
        return ResponseEntity.noContent().build();
    }
}