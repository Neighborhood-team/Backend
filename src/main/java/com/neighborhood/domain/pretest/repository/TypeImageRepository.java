package com.neighborhood.domain.pretest.repository;

import com.neighborhood.domain.pretest.entity.TypeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeImageRepository extends JpaRepository<TypeImage, Long> {
}
