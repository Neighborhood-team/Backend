package com.neighborhood.domain.member.repository;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.member.entity.FamilyRole;
import com.neighborhood.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findFirstByFamilyOrderByCreatedDateAsc(Family family);

    Optional<Member> findByPhone(String phone);

    boolean existsByPhone(String phone);

    List<Member> findByFamily_FamilyId(Long familyId);
}
