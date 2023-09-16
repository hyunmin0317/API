package com.smunity.api.data.repository;

import com.smunity.api.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
