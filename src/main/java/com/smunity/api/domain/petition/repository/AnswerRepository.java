package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
