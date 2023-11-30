package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.RespondDto;
import org.springframework.transaction.annotation.Transactional;

public interface RespondService {
    RespondDto.Response createRespond(Long petitionId, RespondDto.Request respondDto, String token);
    RespondDto.Response getRespondByPetitionId(Long petitionId);
    @Transactional
    RespondDto.Response updateRespond(Long petitionId, RespondDto.Request respondDto, String token);
    void deleteRespond(Long petitionId, String token);
}