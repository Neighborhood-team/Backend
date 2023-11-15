package com.neighborhood.domain.family.repository;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family, Long> {

    Optional<Family> findByFamilyCode(String familyCode);

}