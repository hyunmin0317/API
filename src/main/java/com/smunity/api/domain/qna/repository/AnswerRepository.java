package com.smunity.api.domain.qna.repository;

import com.smunity.api.domain.qna.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
