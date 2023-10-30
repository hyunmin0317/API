package com.smunity.api.domain.petition.controller;

import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.service.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/petitions")
public class PetitionController {
    private final PetitionService petitionService;

    @GetMapping()
    ResponseEntity<List<PetitionDto.Response>> getAllPetitions() {
        List<PetitionDto.Response> petitionDtoList = petitionService.getAllPetitions();
        return ResponseEntity.status(HttpStatus.OK).body(petitionDtoList);
    }

    @PostMapping()
    public ResponseEntity<PetitionDto.Response> createPetition(@RequestBody PetitionDto.Request petitionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        PetitionDto.Response petition = petitionService.createPetition(petitionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @GetMapping(value = "/{petitionId}")
    ResponseEntity<PetitionDto.Response> getPetitionById(@PathVariable Long petitionId) {
        PetitionDto.Response petitionDto = petitionService.getPetitionById(petitionId);
        return ResponseEntity.status(HttpStatus.OK).body(petitionDto);
    }

    @PutMapping(value = "/{petitionId}")
    ResponseEntity<PetitionDto.Response> updatePetition(@PathVariable Long petitionId, @RequestBody PetitionDto.Request petitionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        PetitionDto.Response petition = petitionService.updatePetition(petitionId, petitionDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(petition);
    }

    @DeleteMapping(value = "/{petitionId}")
    ResponseEntity<?> deletePetition(@PathVariable Long petitionId, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        petitionService.deletePetition(petitionId, token);
        return ResponseEntity.noContent().build();
    }
}
