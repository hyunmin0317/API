package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class DepartmentRepositoryTest {
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void findByNameTest(){
        Optional<Department> department = departmentRepository.findByName("컴퓨터과학전공");
        if (!department.isEmpty())
            System.out.println(department.get());
    }
}
