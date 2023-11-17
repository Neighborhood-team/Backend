package com.neighborhood.domain.contextcopy.repository;

import com.neighborhood.domain.contextcopy.dto.ContextCopyDto;

import java.util.List;

public interface ContextCopyRepositoryCustom {
    List<ContextCopyDto> findByFamily_type(String context_role);
}