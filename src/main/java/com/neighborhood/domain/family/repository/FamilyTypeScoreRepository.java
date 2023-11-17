package com.neighborhood.domain.family.repository;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.entity.FamilyTypeScore;
import com.neighborhood.domain.pretest.entity.TestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyTypeScoreRepository extends JpaRepository<FamilyTypeScore, Long> {

    Optional<FamilyTypeScore> findByFamilyAndTestType(Family family, TestType testType);
}
