package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.PetitionResponseDto;
import java.util.List;


public interface PetitionService {
    List<PetitionResponseDto> findAllPetitions();
    PetitionResponseDto getPetition(Long id);
}
