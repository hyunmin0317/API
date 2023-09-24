package com.smunity.api.domain.petition.service.impl;

import com.smunity.api.domain.petition.domain.Petition;
import com.smunity.api.domain.petition.dto.PetitionResponseDto;
import com.smunity.api.domain.petition.repository.PetitionRepository;
import com.smunity.api.domain.petition.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PetitionServiceImpl implements PetitionService {

    @Autowired
    private PetitionRepository petitionRepository;

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
}
