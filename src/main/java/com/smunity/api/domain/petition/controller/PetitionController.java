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
    PetitionService petitionService;

    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    @GetMapping()
    List<PetitionResponseDto> findAllPetitions() {
        return petitionService.findAllPetitions();
    }

    @PostMapping()
    public PetitionResponseDto createPetition(@RequestBody PetitionDto petitionDto) {
        return petitionService.savePetition(petitionDto);
    }

    @GetMapping(value = "/{id}")
    PetitionResponseDto getPetition(@PathVariable Long id) {
        return petitionService.getPetition(id);
    }
}
