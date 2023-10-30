package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.PetitionDto;
import java.util.List;


public interface PetitionService {
    List<PetitionDto.Response> getAllPetitions();
    PetitionDto.Response createPetition(PetitionDto.Request petitionDto, String token);
    PetitionDto.Response getPetitionById(Long id);
    PetitionDto.Response updatePetition(Long id, PetitionDto.Request petitionDto, String token);
    void deletePetition(Long id, String token);
}
