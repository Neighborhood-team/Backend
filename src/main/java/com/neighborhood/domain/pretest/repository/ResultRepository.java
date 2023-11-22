package com.neighborhood.domain.pretest.repository;

import com.neighborhood.domain.pretest.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
