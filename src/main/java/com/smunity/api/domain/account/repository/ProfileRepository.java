package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
