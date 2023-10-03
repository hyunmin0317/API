package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.domain.Agreement;
import com.smunity.api.domain.petition.dto.AgreementDto;
import com.smunity.api.domain.petition.repository.AgreementRepository;
import com.smunity.api.domain.petition.service.AgreementService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AgreementServiceImpl implements AgreementService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AgreementRepository agreementRepository;

    public AgreementServiceImpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, AgreementRepository agreementRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.agreementRepository = agreementRepository;
    }

    @Override
    public List<AgreementDto> getAgreementUsersByPetitionId(Long petitionId) {
        List<Agreement> agreementList = agreementRepository.findAllByPetitionId(petitionId);
        for (Agreement agreement: agreementList)
            System.out.println(agreement);
        return AgreementDto.toDtos(agreementList);
    }

    @Override
    public void createAgreement(Long petitionId, String token) {

    }
}
