package com.smunity.api.domain.petition.controller;

import com.smunity.api.domain.petition.dto.CommentDto;
import com.smunity.api.domain.petition.dto.RespondDto;
import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.service.CommentService;
import com.smunity.api.domain.petition.service.RespondService;
import com.smunity.api.domain.petition.service.PetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/petitions")
public class PetitionController {
    private final PetitionService petitionService;

    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
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

    @GetMapping(value = "/{petitionId}")
    ResponseEntity<PetitionDto> getPetition(@PathVariable Long petitionId) {
        PetitionDto petitionDto = petitionService.getPetition(petitionId);
        return ResponseEntity.status(HttpStatus.OK).body(petitionDto);
    }

    @PutMapping(value = "/{petitionId}")
    ResponseEntity<PetitionDto> changePetition(@PathVariable Long petitionId, @RequestBody PetitionDto petitionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        PetitionDto petition = petitionService.changePetition(petitionId, petitionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @DeleteMapping(value = "/{petitionId}")
    ResponseEntity<?> deletePetition(@PathVariable Long petitionId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        petitionService.deletePetition(petitionId, token);
        return ResponseEntity.noContent().build();
    }
}
