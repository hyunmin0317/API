package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface YearRepository extends JpaRepository<Year, Long> {
    Optional<Year> findByYear(String year);
}
