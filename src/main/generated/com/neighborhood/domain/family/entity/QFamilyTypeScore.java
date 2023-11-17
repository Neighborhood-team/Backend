package com.neighborhood.domain.family.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFamilyTypeScore is a Querydsl query type for FamilyTypeScore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFamilyTypeScore extends EntityPathBase<FamilyTypeScore> {

    private static final long serialVersionUID = -719021540L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFamilyTypeScore familyTypeScore = new QFamilyTypeScore("familyTypeScore");

    public final QFamily family;

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final NumberPath<Long> scoreId = createNumber("scoreId", Long.class);

    public final EnumPath<com.neighborhood.domain.pretest.entity.TestType> testType = createEnum("testType", com.neighborhood.domain.pretest.entity.TestType.class);

    public QFamilyTypeScore(String variable) {
        this(FamilyTypeScore.class, forVariable(variable), INITS);
    }

    public QFamilyTypeScore(Path<? extends FamilyTypeScore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFamilyTypeScore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFamilyTypeScore(PathMetadata metadata, PathInits inits) {
        this(FamilyTypeScore.class, metadata, inits);
    }

    public QFamilyTypeScore(Class<? extends FamilyTypeScore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.family = inits.isInitialized("family") ? new QFamily(forProperty("family")) : null;
    }

}

