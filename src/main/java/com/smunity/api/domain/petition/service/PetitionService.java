package com.smunity.api.domain.petition.service;

import com.querydsl.core.types.Predicate;
import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.entity.Petition;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.error.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PetitionService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;

    public Page<PetitionDto.Response> getAllPetitions(Predicate predicate, Pageable pageable) {
        Page<Petition> petitionPage = petitionRepository.findAll(predicate, pageable);
        return PetitionDto.Response.from(petitionPage);
    }

    public PetitionDto.Response getPetitionById(Long id) {
        Petition petition = petitionRepository.findById(id).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        return PetitionDto.Response.from(petition);
    }

    @Transactional
    public PetitionDto.Response createPetition(PetitionDto.Request petitionDto, String token) {
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.getByUsername(username).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        Petition petition = petitionDto.toEntity(user);
        Petition savePetition = petitionRepository.save(petition);
        return PetitionDto.Response.from(savePetition);
    }

    @Transactional
    public PetitionDto.Response updatePetition(Long id, PetitionDto.Request petitionDto, String token) {
        Petition petition = petitionRepository.findById(id).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(petition.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        petition.update(petitionDto.getSubject(), petitionDto.getContent(), petitionDto.getCategory(), petitionDto.getAnonymous());
        Petition changedPetition = petitionRepository.save(petition);
        return PetitionDto.Response.from(changedPetition);
    }

    @Transactional
    public void deletePetition(Long id, String token) {
        Petition petition = petitionRepository.findById(id).orElseThrow(() -> new RestException(HttpStatus.NO_CONTENT));
        if (!jwtTokenProvider.validateToken(token))
            throw new RestException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(petition.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new RestException(HttpStatus.FORBIDDEN);
        petitionRepository.delete(petition);
    }
}
