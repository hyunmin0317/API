package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.domain.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AgreementRepository extends JpaRepository<Agreement, Long> {
}
