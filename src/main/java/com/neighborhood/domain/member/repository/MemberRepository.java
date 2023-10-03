package com.neighborhood.domain.member.repository;

import com.neighborhood.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
