package com.smunity.api.domain.petition.repository;

import com.querydsl.core.types.Predicate;
import com.smunity.api.domain.petition.entity.Petition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PetitionRepository extends JpaRepository<Petition, Long>, QuerydslPredicateExecutor<Petition> {
    Page<Petition> findAll(Predicate predicate, Pageable pageable);
}
