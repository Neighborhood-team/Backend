package com.neighborhood.domain.todayquestion.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodayQuestionAnswer is a Querydsl query type for TodayQuestionAnswer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodayQuestionAnswer extends EntityPathBase<TodayQuestionAnswer> {

    private static final long serialVersionUID = -1211731272L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodayQuestionAnswer todayQuestionAnswer = new QTodayQuestionAnswer("todayQuestionAnswer");

    public final NumberPath<Long> answerId = createNumber("answerId", Long.class);

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> createdDate = createDate("createdDate", java.time.LocalDate.class);

    public final com.neighborhood.domain.family.entity.QFamily family;

    public final com.neighborhood.domain.member.entity.QMember member;

    public final QTodayQuestion todayQuestion;

    public QTodayQuestionAnswer(String variable) {
        this(TodayQuestionAnswer.class, forVariable(variable), INITS);
    }

    public QTodayQuestionAnswer(Path<? extends TodayQuestionAnswer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodayQuestionAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodayQuestionAnswer(PathMetadata metadata, PathInits inits) {
        this(TodayQuestionAnswer.class, metadata, inits);
    }

    public QTodayQuestionAnswer(Class<? extends TodayQuestionAnswer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.family = inits.isInitialized("family") ? new com.neighborhood.domain.family.entity.QFamily(forProperty("family")) : null;
        this.member = inits.isInitialized("member") ? new com.neighborhood.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
        this.todayQuestion = inits.isInitialized("todayQuestion") ? new QTodayQuestion(forProperty("todayQuestion")) : null;
    }

}

