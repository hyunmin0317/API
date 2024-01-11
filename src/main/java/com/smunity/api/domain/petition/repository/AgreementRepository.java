package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    List<Agreement> findAllByPetitionId(Long petitionId);

    boolean existsByPetitionIdAndUserId(Long petitionId, Long userId);
}
