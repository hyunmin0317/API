package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.domain.Year;
import org.springframework.data.jpa.repository.JpaRepository;


public interface YearRepository extends JpaRepository<Year, Long> {
    Year getByYear(String year);
}
