package com.smunity.api.domain.petition.repository;

import com.smunity.api.domain.petition.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPetitionId(Long petitionId);
    Optional<Comment> findByPetitionId(Long petitionId);
    boolean existsByPetitionId(Long petitionId);
}
