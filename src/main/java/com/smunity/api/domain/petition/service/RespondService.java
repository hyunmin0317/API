package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.RespondDto;


public interface RespondService {
    RespondDto.Response createRespond(Long petitionId, RespondDto.Request respondDto, String token);
    RespondDto.Response getRespondByPetitionId(Long petitionId);
    RespondDto.Response updateRespond(Long petitionId, RespondDto.Request respondDto, String token);
    void deleteRespond(Long petitionId, String token);
}
