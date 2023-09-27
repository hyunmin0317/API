package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.domain.Year;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class YearRepositoryTest {
    @Autowired
    YearRepository yearRepository;

    @Test
    void findByYearTest(){
        Year year = yearRepository.getByYear("2019");
        System.out.println(year);
    }
}
