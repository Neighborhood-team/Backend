package com.neighborhood.domain.context_copy.repository;

import com.neighborhood.domain.context_copy.entity.ContextCopy;
import com.neighborhood.domain.family.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContextCopyRepository extends JpaRepository<ContextCopy, Long> {

}
