package com.neighborhood.domain.todayquestion.repository;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.todayquestion.entity.TodayQuestionAnswer;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodayQuestionAnswerRepository extends JpaRepository<TodayQuestionAnswer, Long> {

    Optional<TodayQuestionAnswer> findByMemberAndCreatedDate(Member member, LocalDate createdDate);

    List<TodayQuestionAnswer> findAllByFamilyAndCreatedDate(Family family, LocalDate createdDate);
}
