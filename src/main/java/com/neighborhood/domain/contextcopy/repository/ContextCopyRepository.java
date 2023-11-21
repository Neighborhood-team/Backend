package com.neighborhood.domain.contextcopy.repository;

import com.neighborhood.domain.contextcopy.entity.ContextCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContextCopyRepository extends JpaRepository<ContextCopy, Long>,ContextCopyRepositoryCustom {

    //context_role
}
