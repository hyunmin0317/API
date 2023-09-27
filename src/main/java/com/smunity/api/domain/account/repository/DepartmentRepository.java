package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department getByName(String name);
}
