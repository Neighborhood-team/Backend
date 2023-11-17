package com.neighborhood.domain.todaymood.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodayMood is a Querydsl query type for TodayMood
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodayMood extends EntityPathBase<TodayMood> {

    private static final long serialVersionUID = -900195462L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodayMood todayMood = new QTodayMood("todayMood");

    public final DateTimePath<java.time.LocalDateTime> create_date = createDateTime("create_date", java.time.LocalDateTime.class);

    public final com.neighborhood.domain.member.entity.QMember member;

    public final StringPath message = createString("message");

    public final StringPath mood = createString("mood");

    public final NumberPath<Long> todayMood_id = createNumber("todayMood_id", Long.class);

    public QTodayMood(String variable) {
        this(TodayMood.class, forVariable(variable), INITS);
    }

    public QTodayMood(Path<? extends TodayMood> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodayMood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodayMood(PathMetadata metadata, PathInits inits) {
        this(TodayMood.class, metadata, inits);
    }

    public QTodayMood(Class<? extends TodayMood> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.neighborhood.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

