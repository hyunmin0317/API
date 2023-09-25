package com.smunity.api.domain.petition.controller;


import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.service.PetitionService;
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
    List<PetitionDto> findAllPetitions() {
        return petitionService.findAllPetitions();
    }

    @PostMapping()
    public PetitionDto createPetition(@RequestBody PetitionDto petitionDto, @RequestHeader(value = "X-AUTH-TOKEN") String token) {
        return petitionService.savePetition(petitionDto, token);
    }

    @GetMapping(value = "/{id}")
    PetitionDto getPetition(@PathVariable Long id) {
        return petitionService.getPetition(id);
    }

    @PutMapping(value = "/{id}")
    PetitionDto getPetition(@PathVariable Long id, @RequestBody PetitionDto petitionDto) {
        return petitionService.changePetition(id, petitionDto);
    }

    @DeleteMapping(value = "/{id}")
    String deletePetition(@PathVariable Long id) {
        petitionService.deletePetition(id);
        return "정상적으로 삭제되었습니다.";
    }
}
