package com.smunity.api.domain.petition.controller;

import com.smunity.api.domain.petition.dto.RespondDto;
import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.service.RespondService;
import com.smunity.api.domain.petition.service.PetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/petitions")
public class PetitionController {
    private final PetitionService petitionService;

    private final RespondService respondService;

    public PetitionController(PetitionService petitionService, RespondService respondService) {
        this.petitionService = petitionService;
        this.respondService = respondService;
    }

    @GetMapping()
    ResponseEntity<List<PetitionDto>> findAllPetitions() {
        List<PetitionDto> petitionDtoList = petitionService.findAllPetitions();
        return ResponseEntity.status(HttpStatus.OK).body(petitionDtoList);
    }

    @PostMapping()
    public ResponseEntity<PetitionDto> createPetition(@RequestBody PetitionDto petitionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        PetitionDto petition = petitionService.createPetition(petitionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<PetitionDto> getPetition(@PathVariable Long id) {
        PetitionDto petitionDto = petitionService.getPetition(id);
        return ResponseEntity.status(HttpStatus.OK).body(petitionDto);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<PetitionDto> changePetition(@PathVariable Long id, @RequestBody PetitionDto petitionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        PetitionDto petition = petitionService.changePetition(id, petitionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> deletePetition(@PathVariable Long id, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        petitionService.deletePetition(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping(value = "/answers")
    ResponseEntity<List<RespondDto>> findAllAnswers() {
        List<RespondDto> respondDtoList = respondService.findAllAnswers();
        return ResponseEntity.status(HttpStatus.OK).body(respondDtoList);
    }

    @PostMapping(value = "/answers")
    public ResponseEntity<RespondDto> createAnswer(@RequestBody RespondDto respondDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        RespondDto answer = respondService.createAnswer(respondDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping(value = "/answers/{id}")
    ResponseEntity<RespondDto> getAnswer(@PathVariable Long id) {
        RespondDto respondDto = respondService.getAnswer(id);
        return ResponseEntity.status(HttpStatus.OK).body(respondDto);
    }

    @PutMapping(value = "/answers/{id}")
    ResponseEntity<RespondDto> changeAnswer(@PathVariable Long id, @RequestBody RespondDto respondDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        RespondDto answer = respondService.changeAnswer(id, respondDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @DeleteMapping(value = "/answers/{id}")
    ResponseEntity<String> deleteAnswer(@PathVariable Long id, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        respondService.deleteAnswer(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
