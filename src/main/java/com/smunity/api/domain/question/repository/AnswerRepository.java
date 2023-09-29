package com.smunity.api.domain.question.repository;

import com.smunity.api.domain.question.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
