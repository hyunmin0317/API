package com.smunity.api.domain.question.repository;

import com.smunity.api.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Long> {
}
