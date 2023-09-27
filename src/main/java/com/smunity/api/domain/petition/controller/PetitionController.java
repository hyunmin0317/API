package com.smunity.api.domain.petition.controller;


import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.service.PetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/petitions")
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
    ResponseEntity<String> deletePetition(@PathVariable Long id) {
        petitionService.deletePetition(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
