package com.smunity.api.domain.petition.service;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.dto.RespondDto;
import com.smunity.api.domain.petition.entity.Petition;
import com.smunity.api.domain.petition.entity.Respond;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.repository.RespondRepository;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.error.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RespondService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final RespondRepository respondRepository;

    @Transactional
    public RespondDto.Response createRespond(Long petitionId, RespondDto.Request respondDto, String token) {
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        if (respondRepository.existsByPetitionId(petitionId))
            throw new RestException(HttpStatus.CONFLICT);
        Petition petition = petitionRepository.findById(petitionId).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.getByUsername(username).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        Respond respond = respondDto.toEntity(user, petition);
        Respond saveRespond = respondRepository.save(respond);
        return RespondDto.Response.from(saveRespond);
    }

    public RespondDto.Response getRespondByPetitionId(Long petitionId) {
        Respond respond = respondRepository.findByPetitionId(petitionId).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        return RespondDto.Response.from(respond);
    }

    @Transactional
    public RespondDto.Response updateRespond(Long petitionId, RespondDto.Request respondDto, String token) {
        Respond respond = respondRepository.findByPetitionId(petitionId).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        respond.update(respondDto.getContent());
        Respond changedRespond = respondRepository.save(respond);
        return RespondDto.Response.from(changedRespond);
    }

    @Transactional
    public void deleteRespond(Long petitionId, String token) {
        Respond respond = respondRepository.findByPetitionId(petitionId).orElseThrow(() -> new RestException(HttpStatus.NO_CONTENT));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        respondRepository.delete(respond);
    }
}
