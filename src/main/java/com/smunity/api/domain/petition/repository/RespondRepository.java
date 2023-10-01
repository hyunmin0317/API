package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.domain.Petition;
import com.smunity.api.domain.petition.domain.Respond;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface RespondRepository extends JpaRepository<Respond, Long> {
    Optional<Respond> findByPetitionId(Long petitionId);
    boolean existsByPetitionId(Long petitionId);
}
