package com.neighborhood.domain.todayquestion.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTodayQuestion is a Querydsl query type for TodayQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodayQuestion extends EntityPathBase<TodayQuestion> {

    private static final long serialVersionUID = -1142689190L;

    public static final QTodayQuestion todayQuestion = new QTodayQuestion("todayQuestion");

    public final StringPath content = createString("content");

    public final NumberPath<Long> questionId = createNumber("questionId", Long.class);

    public final StringPath subText = createString("subText");

    public final EnumPath<com.neighborhood.domain.pretest.entity.TestType> type = createEnum("type", com.neighborhood.domain.pretest.entity.TestType.class);

    public QTodayQuestion(String variable) {
        super(TodayQuestion.class, forVariable(variable));
    }

    public QTodayQuestion(Path<? extends TodayQuestion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTodayQuestion(PathMetadata metadata) {
        super(TodayQuestion.class, metadata);
    }

}

