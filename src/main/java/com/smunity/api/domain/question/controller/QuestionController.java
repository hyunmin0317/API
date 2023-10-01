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
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    public QuestionController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
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

    @GetMapping(value = "/{id}")
    ResponseEntity<QuestionDto> getQuestion(@PathVariable Long id) {
        QuestionDto petitionDto = questionService.getQuestion(id);
        return ResponseEntity.status(HttpStatus.OK).body(petitionDto);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<QuestionDto> changeQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        QuestionDto petition = questionService.changeQuestion(id, questionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> deleteQuestion(@PathVariable Long id, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        questionService.deleteQuestion(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping(value = "/{id}/answers")
    public ResponseEntity<AnswerDto> createAnswer(@PathVariable Long id, @RequestBody AnswerDto answerDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        AnswerDto answer = answerService.createAnswer(id, answerDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping(value = "/{id}/answers")
    ResponseEntity<AnswerDto> getAnswer(@PathVariable Long id) {
        AnswerDto answerDto = answerService.getAnswer(id);
        return ResponseEntity.status(HttpStatus.OK).body(answerDto);
    }

    @PutMapping(value = "/{id}/answers")
    ResponseEntity<AnswerDto> changeAnswer(@PathVariable Long id, @RequestBody AnswerDto answerDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        AnswerDto answer = answerService.changeAnswer(id, answerDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @DeleteMapping(value = "/{id}/answers")
    ResponseEntity<String> deleteAnswer(@PathVariable Long id, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        answerService.deleteAnswer(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
