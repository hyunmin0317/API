package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
