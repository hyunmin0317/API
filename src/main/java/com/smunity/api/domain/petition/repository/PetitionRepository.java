package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.entity.Petition;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PetitionRepository extends JpaRepository<Petition, Long> {
}
