package com.smunity.api.domain.account.repository;

import com.smunity.api.domain.account.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile getByName(String name);
}
