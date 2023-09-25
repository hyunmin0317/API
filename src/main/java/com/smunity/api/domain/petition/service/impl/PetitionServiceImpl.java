package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.account.domain.User;
import com.smunity.api.domain.account.repository.UserRepository;
import com.smunity.api.domain.petition.domain.Petition;
import com.smunity.api.domain.petition.dto.PetitionDto;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.service.PetitionService;
import com.smunity.api.global.config.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public List<PetitionDto> findAllPetitions() {
        List<PetitionDto> petitionDtoList = new ArrayList<>();
        List<Petition> petitionList = petitionRepository.findAll();
        for (Petition petition: petitionList) {
            PetitionDto petitionDto = PetitionDto.toDto(petition);
            petitionDtoList.add(petitionDto);
        }
        return petitionDtoList;
    }

    @Override
    public PetitionDto getPetition(Long id) {
        Optional<Petition> petitionOptional = petitionRepository.findById(id);
        if (!petitionOptional.isEmpty())
            return PetitionDto.toDto(petitionOptional.get());
        return null;
    }

    @Override
    public PetitionDto savePetition(PetitionDto petitionDto, String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsername(token);
            User user = userRepository.getByUsername(username);
            Petition petition = petitionDto.toEntity(user);
            Petition savePetition = petitionRepository.save(petition);
            PetitionDto petitionResponseDto = PetitionDto.toDto(savePetition);
            return petitionResponseDto;
        }
        return null;
    }

    @Override
    public PetitionDto changePetition(Long id, PetitionDto petitionDto) {
        Optional<Petition> petitionOptional = petitionRepository.findById(id);
        if (petitionOptional.isEmpty())
            return null;
        Petition petition = petitionOptional.get();
        petition.setSubject(petitionDto.getSubject());
        petition.setContent(petitionDto.getContent());
        petition.setCategory(petitionDto.getCategory());
        petition.setAnonymous(petitionDto.getAnonymous());
        Petition changedPetition = petitionRepository.save(petition);
        return PetitionDto.toDto(changedPetition);
    }

    @Override
    public void deletePetition(Long id) {
        if (petitionRepository.existsById(id))
            petitionRepository.deleteById(id);
    }
}
