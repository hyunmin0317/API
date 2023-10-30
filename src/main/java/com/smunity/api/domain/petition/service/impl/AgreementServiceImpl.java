package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.entity.Agreement;
import com.smunity.api.domain.petition.entity.Petition;
import com.smunity.api.domain.petition.dto.AgreementDto;
import com.smunity.api.domain.petition.repository.AgreementRepository;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.service.AgreementService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.error.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final AgreementRepository agreementRepository;

    @Override
    public List<AgreementDto> getAgreementUsersByPetitionId(Long petitionId) {
        petitionRepository.findById(petitionId).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        List<Agreement> agreementList = agreementRepository.findAllByPetitionId(petitionId);
        return AgreementDto.of(agreementList);
    }

    @Override
    public AgreementDto createAgreement(Long petitionId, String token) {
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (jwtTokenProvider.getUsername(token).equals(petition.getAuthor().getUsername()))
            throw new RestException(HttpStatus.FORBIDDEN);
        User user = userRepository.getByUsername(jwtTokenProvider.getUsername(token));
        if (agreementRepository.existsByPetitionIdAndUserId(petitionId, user.getId()))
            throw new RestException(HttpStatus.CONFLICT);
        Agreement agreement = AgreementDto.toEntity(user, petition);
        Agreement saveAgreement = agreementRepository.save(agreement);
        return AgreementDto.of(saveAgreement);
    }
}
