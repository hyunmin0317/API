package com.smunity.api.data.repository;

import com.smunity.api.domain.account.entity.User;
import com.smunity.api.domain.account.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void findByNameTest(){
        List<User> userList = userRepository.findAll();
        for(User user: userList) {
            System.out.println(user.getUsername());
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
        }
    }
}
