package com.smunity.api.domain.petition.controller;


import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.dto.PetitionResponseDto;
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
    List<PetitionResponseDto> findAllPetitions() {
        return petitionService.findAllPetitions();
    }

    @PostMapping()
    public PetitionResponseDto createPetition(@RequestBody PetitionDto petitionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        return petitionService.savePetition(petitionDto, token);
    }

    @GetMapping(value = "/{id}")
    PetitionResponseDto getPetition(@PathVariable Long id) {
        return petitionService.getPetition(id);
    }

    @PutMapping(value = "/{id}")
    PetitionResponseDto getPetition(@PathVariable Long id, @RequestBody PetitionDto petitionDto) {
        return petitionService.changePetition(id, petitionDto);
    }
}
