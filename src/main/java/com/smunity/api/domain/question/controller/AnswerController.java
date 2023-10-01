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
@RequestMapping("/api/questions/{questionId}/answers")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping()
    public ResponseEntity<AnswerDto> createAnswer(@PathVariable Long questionId, @RequestBody AnswerDto answerDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        AnswerDto answer = answerService.createAnswer(questionId, answerDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping()
    ResponseEntity<AnswerDto> getAnswer(@PathVariable Long questionId) {
        AnswerDto answerDto = answerService.getAnswer(questionId);
        return ResponseEntity.status(HttpStatus.OK).body(answerDto);
    }

    @PutMapping()
    ResponseEntity<AnswerDto> changeAnswer(@PathVariable Long questionId, @RequestBody AnswerDto answerDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        AnswerDto answer = answerService.changeAnswer(questionId, answerDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @DeleteMapping()
    ResponseEntity<?> deleteAnswer(@PathVariable Long questionId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        answerService.deleteAnswer(questionId, token);
        return ResponseEntity.noContent().build();
    }
}
