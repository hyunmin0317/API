package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.domain.Petition;
import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.service.PetitionService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import com.smunity.api.global.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PetitionServiceImpl implements PetitionService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;

    public PetitionServiceImpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PetitionRepository petitionRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.petitionRepository = petitionRepository;
    }

    @Override
    public List<PetitionDto> getAllPetitions() {
        List<Petition> petitionList = petitionRepository.findAll();
        return PetitionDto.toDtos(petitionList);
    }

    @Override
    public PetitionDto getPetitionById(Long id) {
        Petition petition = petitionRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        return PetitionDto.toDto(petition);
    }

    @Override
    public PetitionDto createPetition(PetitionDto petitionDto, String token) {
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.getByUsername(username);
        Petition petition = petitionDto.toEntity(user);
        Petition savePetition = petitionRepository.save(petition);
        PetitionDto petitionResponseDto = PetitionDto.toDto(savePetition);
        return petitionResponseDto;
    }

    @Override
    public PetitionDto updatePetition(Long id, PetitionDto petitionDto, String token) {
        Petition petition = petitionRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(petition.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new CustomException(HttpStatus.FORBIDDEN);
        petition.setSubject(petitionDto.getSubject());
        petition.setContent(petitionDto.getContent());
        petition.setCategory(petitionDto.getCategory());
        petition.setAnonymous(petitionDto.getAnonymous());
        Petition changedPetition = petitionRepository.save(petition);
        return PetitionDto.toDto(changedPetition);
    }

    @Override
    public void deletePetition(Long id, String token) {
        Petition petition = petitionRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NO_CONTENT));
        if (!jwtTokenProvider.validateToken(token))
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        if (!jwtTokenProvider.getUsername(token).equals(petition.getAuthor().getUsername()) && !jwtTokenProvider.getIsSuperuser(token))
            throw new CustomException(HttpStatus.FORBIDDEN);
        petitionRepository.delete(petition);
    }
}
