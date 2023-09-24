package com.smunity.api.domain.petition.controller;


import com.smunity.api.domain.petition.dto.PetitionResponseDto;
import com.smunity.api.domain.petition.service.PetitionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/{id}")
    PetitionResponseDto getPetition(@PathVariable Long id) {
        return petitionService.getPetition(id);
    }
}
