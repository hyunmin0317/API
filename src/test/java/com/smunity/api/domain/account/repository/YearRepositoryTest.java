package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.entity.Year;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class YearRepositoryTest {
    @Autowired
    YearRepository yearRepository;

    @Test
    void findByYearTest(){
        Optional<Year> year = yearRepository.findByYear("2019");
        if (!year.isEmpty())
            System.out.println(year.get());
    }
}
