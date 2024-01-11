package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.entity.Petition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class PetitionRepositoryTest {
    @Autowired
    PetitionRepository petitionRepository;

    @Test
    void findAllest() {
        List<Petition> petitionList = petitionRepository.findAll();
        for (Petition petition : petitionList) {
            System.out.println(petition);
        }
    }
}
