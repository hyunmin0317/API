package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.domain.Petition;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PetitionRepository extends JpaRepository<Petition, Long> {
}
