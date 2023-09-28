package com.smunity.api.domain.qna.repository;

import com.smunity.api.domain.qna.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Long> {
}
