package com.smunity.api.domain.petition.service;

import com.querydsl.core.types.Predicate;
import com.smunity.api.domain.petition.dto.PetitionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface PetitionService {
    @Transactional
    Page<PetitionDto.Response> getAllPetitions(Predicate predicate, Pageable pageable);

    PetitionDto.Response createPetition(PetitionDto.Request petitionDto, String token);

    @Transactional
    PetitionDto.Response getPetitionById(Long id);

    @Transactional
    PetitionDto.Response updatePetition(Long id, PetitionDto.Request petitionDto, String token);

    void deletePetition(Long id, String token);
}
