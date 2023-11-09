package com.smunity.api.domain.petition.repository;

import com.querydsl.core.types.Predicate;
import com.smunity.api.domain.petition.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface CommentRepository extends JpaRepository<Comment, Long>, QuerydslPredicateExecutor<Comment> {
    Page<Comment> findAll(Predicate predicate, Pageable pageable);
}
