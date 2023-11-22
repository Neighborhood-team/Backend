package com.neighborhood.domain.todaymood.repository;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.todaymood.entity.TodayMood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodayMoodRepository extends JpaRepository<TodayMood, Long> {
    TodayMood findByMember_MemberId(Long memberId);
}
