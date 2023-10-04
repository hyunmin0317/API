package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.entity.Respond;
import com.smunity.api.domain.petition.entity.Petition;
import com.smunity.api.domain.petition.dto.RespondDto;
import com.smunity.api.domain.petition.repository.RespondRepository;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.service.RespondService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RespondServiceImpl implements RespondService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final RespondRepository respondRepository;

    @Override
    public RespondDto createRespond(Long petitionId, RespondDto respondDto, String token) {
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getIsSuperuser(token))
            throw new CustomException(HttpStatus.FORBIDDEN);
        if (respondRepository.existsByPetitionId(petitionId))
            throw new CustomException(HttpStatus.CONFLICT);
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.getByUsername(username);
        Respond respond = respondDto.toEntity(user, petition);
        Respond saveRespond = respondRepository.save(respond);
        return RespondDto.toDto(saveRespond);
    }

    @Override
    public RespondDto getRespondByPetitionId(Long petitionId) {
        Respond respond = respondRepository.findByPetitionId(petitionId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        return RespondDto.toDto(respond);
    }

    @Override
    public RespondDto updateRespond(Long petitionId, RespondDto respondDto, String token) {
        Respond respond = respondRepository.findByPetitionId(petitionId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getIsSuperuser(token))
            throw new CustomException(HttpStatus.FORBIDDEN);
        respond.setContent(respondDto.getContent());
        Respond changedRespond = respondRepository.save(respond);
        return RespondDto.toDto(changedRespond);
    }

    @Override
    public void deleteRespond(Long petitionId, String token) {
        Respond respond = respondRepository.findByPetitionId(petitionId)
                .orElseThrow(() -> new CustomException(HttpStatus.NO_CONTENT));
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getIsSuperuser(token))
            throw new CustomException(HttpStatus.FORBIDDEN);
        respondRepository.delete(respond);
    }
}
