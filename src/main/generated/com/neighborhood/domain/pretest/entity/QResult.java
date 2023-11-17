package com.neighborhood.domain.pretest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResult is a Querydsl query type for Result
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResult extends EntityPathBase<Result> {

    private static final long serialVersionUID = -673930952L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResult result = new QResult("result");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final com.neighborhood.domain.member.entity.QMember member;

    public final StringPath resultCode = createString("resultCode");

    public final NumberPath<Long> resultId = createNumber("resultId", Long.class);

    public final StringPath resultType = createString("resultType");

    public final QTypeImage typeImage;

    public final NumberPath<Long> typeNumber = createNumber("typeNumber", Long.class);

    public final MapPath<TestType, Integer, NumberPath<Integer>> typeScores = this.<TestType, Integer, NumberPath<Integer>>createMap("typeScores", TestType.class, Integer.class, NumberPath.class);

    public QResult(String variable) {
        this(Result.class, forVariable(variable), INITS);
    }

    public QResult(Path<? extends Result> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResult(PathMetadata metadata, PathInits inits) {
        this(Result.class, metadata, inits);
    }

    public QResult(Class<? extends Result> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.neighborhood.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
        this.typeImage = inits.isInitialized("typeImage") ? new QTypeImage(forProperty("typeImage"), inits.get("typeImage")) : null;
    }

}

