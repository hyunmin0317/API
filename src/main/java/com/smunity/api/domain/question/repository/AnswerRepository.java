package com.smunity.api.domain.question.repository;

import com.smunity.api.domain.question.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByQuestionId(Long questionId);
    boolean existsByQuestionId(Long questionId);
}
