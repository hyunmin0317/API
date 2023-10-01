package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.PetitionDto;
import java.util.List;


public interface PetitionService {
    List<PetitionDto> getAllPetitions();
    PetitionDto createPetition(PetitionDto petitionDto, String token);
    PetitionDto getPetitionById(Long id);
    PetitionDto updatePetition(Long id, PetitionDto petitionDto, String token);
    void deletePetition(Long id, String token);
}
