package com.neighborhood.domain.todayquestion.repository;

import com.neighborhood.domain.pretest.entity.TestType;
import com.neighborhood.domain.todayquestion.entity.TodayQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodayQuestionRepository extends JpaRepository<TodayQuestion, Long> {

    List<TodayQuestion> findAllByTypeOrderByQuestionIdAsc(TestType type);

    Integer countAllByType(TestType type);
}

