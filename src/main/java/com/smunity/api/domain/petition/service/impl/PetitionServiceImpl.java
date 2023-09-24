package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.domain.Petition;
import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.dto.PetitionResponseDto;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.service.PetitionService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PetitionServiceImpl implements PetitionService {
    public JwtTokenProvider jwtTokenProvider;
    public UserRepository userRepository;
    private PetitionRepository petitionRepository;

    @Autowired
    public PetitionServiceImpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PetitionRepository petitionRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.petitionRepository = petitionRepository;
    }

    @Override
    public List<PetitionResponseDto> findAllPetitions() {
        List<PetitionResponseDto> petitionResponseDtoList = new ArrayList<>();
        List<Petition> petitionList = petitionRepository.findAll();
        for (Petition petition: petitionList) {
            PetitionResponseDto petitionResponseDto = PetitionResponseDto.petitionResponseDtoBuilder()
                    .id(petition.getId())
                    .subject(petition.getSubject())
                    .content(petition.getContent())
                    .category(petition.getCategory())
                    .anonymous(petition.isAnonymous())
                    .create_date(petition.getCreate_date())
                    .end_date(petition.getEnd_date())
                    .modify_date(petition.getModify_date())
                    .status(petition.getStatus())
                    .author_id(petition.getAuthor().getId())
                    .build();
            petitionResponseDtoList.add(petitionResponseDto);
        }
        return petitionResponseDtoList;
    }

    @Override
    public PetitionResponseDto getPetition(Long id) {
        Petition petition = petitionRepository.findById(id).get();
        PetitionResponseDto petitionResponseDto = PetitionResponseDto.petitionResponseDtoBuilder()
                .id(petition.getId())
                .subject(petition.getSubject())
                .content(petition.getContent())
                .category(petition.getCategory())
                .anonymous(petition.isAnonymous())
                .create_date(petition.getCreate_date())
                .modify_date(petition.getModify_date())
                .end_date(petition.getEnd_date())
                .status(petition.getStatus())
                .author_id(petition.getAuthor().getId())
                .build();
        return petitionResponseDto;
    }

    @Override
    public PetitionResponseDto savePetition(PetitionDto petitionDto, String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsername(token);
            User user = userRepository.getByUsername(username);
            Petition petition = Petition.builder()
                    .subject(petitionDto.getSubject())
                    .content(petitionDto.getContent())
                    .category(petitionDto.getCategory())
                    .anonymous(petitionDto.isAnonymous())
                    .create_date(petitionDto.getCreate_date())
                    .modify_date(petitionDto.getModify_date())
                    .end_date(petitionDto.getEnd_date())
                    .status(petitionDto.getStatus())
                    .author(user)
                    .build();
            Petition savePetition = petitionRepository.save(petition);
            PetitionResponseDto petitionResponseDto = PetitionResponseDto.petitionResponseDtoBuilder()
                    .id(savePetition.getId())
                    .subject(savePetition.getSubject())
                    .content(savePetition.getContent())
                    .category(savePetition.getCategory())
                    .anonymous(savePetition.isAnonymous())
                    .create_date(savePetition.getCreate_date())
                    .modify_date(savePetition.getModify_date())
                    .end_date(savePetition.getEnd_date())
                    .status(savePetition.getStatus())
                    .author_id(savePetition.getAuthor().getId())
                    .build();
            return petitionResponseDto;
        }
        return null;
    }
}
