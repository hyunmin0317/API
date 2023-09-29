package com.smunity.api.domain.question.controller;

import com.smunity.api.domain.question.dto.AnswerDto;
import com.smunity.api.domain.question.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping()
    ResponseEntity<List<AnswerDto>> findAllQuestions() {
        List<AnswerDto> answerDtoList = answerService.findAllAnswers();
        return ResponseEntity.status(HttpStatus.OK).body(answerDtoList);
    }

    @PostMapping()
    public ResponseEntity<AnswerDto> createPetition(@RequestBody AnswerDto answerDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        AnswerDto answer = answerService.createAnswer(answerDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<AnswerDto> getPetition(@PathVariable Long id) {
        AnswerDto answerDto = answerService.getAnswer(id);
        return ResponseEntity.status(HttpStatus.OK).body(answerDto);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<AnswerDto> changePetition(@PathVariable Long id, @RequestBody AnswerDto answerDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        AnswerDto answer = answerService.changeAnswer(id, answerDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> deletePetition(@PathVariable Long id, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        answerService.deleteAnswer(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
