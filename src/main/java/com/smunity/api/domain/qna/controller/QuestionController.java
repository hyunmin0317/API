package com.smunity.api.domain.qna.controller;


import com.smunity.api.domain.qna.dto.QuestionDto;
import com.smunity.api.domain.qna.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
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
    public ResponseEntity<QuestionDto> createPetition(@RequestBody QuestionDto questionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        QuestionDto question = questionService.createQuestion(questionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<QuestionDto> getPetition(@PathVariable Long id) {
        QuestionDto petitionDto = questionService.getQuestion(id);
        return ResponseEntity.status(HttpStatus.OK).body(petitionDto);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<QuestionDto> changePetition(@PathVariable Long id, @RequestBody QuestionDto questionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        QuestionDto petition = questionService.changeQuestion(id, questionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> deletePetition(@PathVariable Long id, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        questionService.deleteQuestion(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
