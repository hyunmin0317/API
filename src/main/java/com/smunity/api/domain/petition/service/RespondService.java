package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.RespondDto;
import java.util.List;


public interface RespondService {
    RespondDto getAnswer(Long petitionId);
    RespondDto createAnswer(Long petitionId, RespondDto respondDto, String token);
    RespondDto changeAnswer(Long petitionId, RespondDto respondDto, String token);
    void deleteAnswer(Long petitionId, String token);
}
