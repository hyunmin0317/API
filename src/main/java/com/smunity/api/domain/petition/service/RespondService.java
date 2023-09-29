package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.RespondDto;
import java.util.List;


public interface RespondService {
    List<RespondDto> findAllAnswers();
    RespondDto getAnswer(Long id);
    RespondDto createAnswer(RespondDto respondDto, String token);
    RespondDto changeAnswer(Long id, RespondDto respondDto, String token);
    void deleteAnswer(Long id, String token);
}
