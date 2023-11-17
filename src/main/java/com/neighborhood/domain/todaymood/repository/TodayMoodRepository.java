package com.neighborhood.domain.todaymood.repository;

import com.neighborhood.domain.todaymood.entity.TodayMood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayMoodRepository extends JpaRepository<TodayMood, Long> {
}
