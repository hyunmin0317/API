package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.petition.dto.AgreementDto;

import java.util.List;


public interface AgreementService {
    List<AgreementDto> getAgreementUsersByPetitionId(Long petitionId);
    void createAgreement(Long petitionId, String token);
}
