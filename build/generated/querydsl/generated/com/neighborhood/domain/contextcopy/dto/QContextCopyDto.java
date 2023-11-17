package com.neighborhood.domain.contextcopy.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.neighborhood.domain.contextcopy.dto.QContextCopyDto is a Querydsl Projection type for ContextCopyDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QContextCopyDto extends ConstructorExpression<ContextCopyDto> {

    private static final long serialVersionUID = 292606763L;

    public QContextCopyDto(com.querydsl.core.types.Expression<String> context, com.querydsl.core.types.Expression<String> context_title) {
        super(ContextCopyDto.class, new Class<?>[]{String.class, String.class}, context, context_title);
    }

}

