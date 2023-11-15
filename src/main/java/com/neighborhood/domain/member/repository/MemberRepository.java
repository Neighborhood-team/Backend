package com.neighborhood.domain.member.repository;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findFirstByFamilyOrderByCreatedDateAsc(Family family);
}
