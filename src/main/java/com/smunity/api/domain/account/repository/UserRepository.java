package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByUsername(String username);

    boolean existsByUsername(String username);
}
