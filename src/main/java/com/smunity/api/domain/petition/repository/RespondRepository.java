package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.domain.Respond;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespondRepository extends JpaRepository<Respond, Long> {
}
