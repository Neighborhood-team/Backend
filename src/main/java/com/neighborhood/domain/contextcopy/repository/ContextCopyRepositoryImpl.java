package com.neighborhood.domain.contextcopy.repository;

import com.neighborhood.domain.contextcopy.dto.ContextCopyDto;
import com.neighborhood.domain.contextcopy.dto.QContextCopyDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.neighborhood.domain.contextcopy.entity.QContextCopy.contextCopy;
import java.util.List;


@RequiredArgsConstructor
public class ContextCopyRepositoryImpl implements ContextCopyRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ContextCopyDto> findByFamily_type(String type){
        JPAQuery result = jpaQueryFactory.select(new QContextCopyDto(
                contextCopy.context,
                contextCopy.context_title))
        .from(contextCopy)
        .where(contextCopy.context_role.eq(type));
        return result.fetch();
    }
}