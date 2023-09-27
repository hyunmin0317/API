package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.domain.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DepartmentRepositoryTest {
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void findByNameTest(){
        Department department = departmentRepository.getByName("컴퓨터과학과");
        Department department2 = departmentRepository.getByName("컴퓨터과학전공");
        System.out.println(department);
        System.out.println(department2);
    }
}
