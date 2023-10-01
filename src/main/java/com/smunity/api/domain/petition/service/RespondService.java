package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.RespondDto;


public interface RespondService {
    RespondDto createRespond(Long petitionId, RespondDto respondDto, String token);
    RespondDto getRespondByPetitionId(Long petitionId);
    RespondDto updateRespond(Long petitionId, RespondDto respondDto, String token);
    void deleteRespond(Long petitionId, String token);
}
