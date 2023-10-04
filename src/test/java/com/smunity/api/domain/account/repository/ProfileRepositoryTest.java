package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.domain.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class ProfileRepositoryTest {
    @Autowired
    ProfileRepository profileRepository;

    @Test
    void findAllest(){
        List<Profile> profileList = profileRepository.findAll();
        for (Profile profile: profileList)
            System.out.println(profile);
    }
}
