package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.account.dto.UserDto;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.domain.Agreement;
import com.smunity.api.domain.petition.domain.Petition;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.service.AgreementService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AgreementServiceImpl implements AgreementService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;

    public AgreementServiceImpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PetitionRepository petitionRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.petitionRepository = petitionRepository;
    }

    @Override
    public List<UserDto> getAgreementUsersByPetitionId(Long petitionId) {
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        return UserDto.toAgreementDtos(petition.getAgreementList());
    }

    @Override
    public void createAgreement(Long petitionId, String token) {

    }
}
