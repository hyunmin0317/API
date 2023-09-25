package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.PetitionDto;

import java.util.List;


public interface PetitionService {
    List<PetitionDto> findAllPetitions();
    PetitionDto getPetition(Long id);
    PetitionDto savePetition(PetitionDto petitionDto, String token);
    PetitionDto changePetition(Long id, PetitionDto petitionDto);
    void deletePetition(Long id);
}
