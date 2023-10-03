package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.account.dto.UserDto;
import com.smunity.api.domain.petition.dto.CommentDto;

import java.util.List;


public interface AgreementService {
    List<UserDto> getAgreementUsersByPetitionId(Long petitionId);
    void createAgreement(Long petitionId, String token);
}
