package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.RespondDto;


public interface RespondService {
    RespondDto getRespond(Long petitionId);
    RespondDto createRespond(Long petitionId, RespondDto respondDto, String token);
    RespondDto changeRespond(Long petitionId, RespondDto respondDto, String token);
    void deleteRespond(Long petitionId, String token);
}
