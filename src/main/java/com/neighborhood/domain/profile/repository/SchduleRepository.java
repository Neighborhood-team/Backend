package com.neighborhood.domain.profile.repository;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.profile.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SchduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByMemberAndEndDateBeforeOrderByStartDateAsc(Member member, LocalDate date);
    List<Schedule> findByMemberAndEndDateAfterOrEndDateEqualsOrderByStartDateAsc(Member member, LocalDate date);
}
