package com.smunity.api.domain.question.controller;


import com.smunity.api.domain.question.dto.AnswerDto;
import com.smunity.api.domain.question.dto.QuestionDto;
import com.smunity.api.domain.question.service.AnswerService;
import com.smunity.api.domain.question.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    ResponseEntity<List<QuestionDto>> findAllQuestions() {
        List<QuestionDto> questionDtoList = questionService.findAllQuestions();
        return ResponseEntity.status(HttpStatus.OK).body(questionDtoList);
    }

    @PostMapping()
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        QuestionDto question = questionService.createQuestion(questionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping(value = "/{questionId}")
    ResponseEntity<QuestionDto> getQuestion(@PathVariable Long questionId) {
        QuestionDto petitionDto = questionService.getQuestion(questionId);
        return ResponseEntity.status(HttpStatus.OK).body(petitionDto);
    }

    @PutMapping(value = "/{questionId}")
    ResponseEntity<QuestionDto> changeQuestion(@PathVariable Long questionId, @RequestBody QuestionDto questionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        QuestionDto petition = questionService.changeQuestion(questionId, questionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @DeleteMapping(value = "/{questionId}")
    ResponseEntity<?> deleteQuestion(@PathVariable Long questionId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        questionService.deleteQuestion(questionId, token);
        return ResponseEntity.noContent().build();
    }
}
