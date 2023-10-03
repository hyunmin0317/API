package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.domain.Agreement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


@SpringBootTest
public class AgreementRepositoryTest {
    @Autowired
    AgreementRepository agreementRepository;

    @Test
    void findAllest(){
        List<Agreement> agreementList = agreementRepository.findAll();
        for(Agreement agreement: agreementList) {
            System.out.println(agreement);
        }
    }
}
