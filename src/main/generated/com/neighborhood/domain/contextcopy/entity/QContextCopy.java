package com.neighborhood.domain.contextcopy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContextCopy is a Querydsl query type for ContextCopy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContextCopy extends EntityPathBase<ContextCopy> {

    private static final long serialVersionUID = 102146490L;

    public static final QContextCopy contextCopy = new QContextCopy("contextCopy");

    public final StringPath context = createString("context");

    public final NumberPath<Long> context_id = createNumber("context_id", Long.class);

    public final StringPath context_role = createString("context_role");

    public final StringPath context_title = createString("context_title");

    public QContextCopy(String variable) {
        super(ContextCopy.class, forVariable(variable));
    }

    public QContextCopy(Path<? extends ContextCopy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContextCopy(PathMetadata metadata) {
        super(ContextCopy.class, metadata);
    }

}

