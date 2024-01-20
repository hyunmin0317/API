package com.smunity.api.domain.question.controller;

import com.smunity.api.domain.question.dto.AnswerDto;
import com.smunity.api.domain.question.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions/{questionId}/answers")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping()
    public ResponseEntity<AnswerDto.Response> createAnswer(@PathVariable Long questionId, @RequestBody AnswerDto.Request answerDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        AnswerDto.Response answer = answerService.createAnswer(questionId, answerDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping()
    ResponseEntity<AnswerDto.Response> getAnswerQuestionId(@PathVariable Long questionId) {
        AnswerDto.Response answerDto = answerService.getAnswerQuestionId(questionId);
        return ResponseEntity.status(HttpStatus.OK).body(answerDto);
    }

    @PutMapping()
    ResponseEntity<AnswerDto.Response> updateAnswer(@PathVariable Long questionId, @RequestBody AnswerDto.Request answerDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        AnswerDto.Response answer = answerService.updateAnswer(questionId, answerDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @DeleteMapping()
    ResponseEntity<?> deleteAnswer(@PathVariable Long questionId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        answerService.deleteAnswer(questionId, token);
        return ResponseEntity.noContent().build();
    }
}