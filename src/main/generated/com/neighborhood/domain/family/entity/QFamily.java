package com.neighborhood.domain.family.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFamily is a Querydsl query type for Family
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFamily extends EntityPathBase<Family> {

    private static final long serialVersionUID = 81153212L;

    public static final QFamily family = new QFamily("family");

    public final StringPath familyCode = createString("familyCode");

    public final NumberPath<Long> familyId = createNumber("familyId", Long.class);

    public final ListPath<FamilyTypeScore, QFamilyTypeScore> familyTypeScores = this.<FamilyTypeScore, QFamilyTypeScore>createList("familyTypeScores", FamilyTypeScore.class, QFamilyTypeScore.class, PathInits.DIRECT2);

    public final ListPath<com.neighborhood.domain.member.entity.Member, com.neighborhood.domain.member.entity.QMember> members = this.<com.neighborhood.domain.member.entity.Member, com.neighborhood.domain.member.entity.QMember>createList("members", com.neighborhood.domain.member.entity.Member.class, com.neighborhood.domain.member.entity.QMember.class, PathInits.DIRECT2);

    public final NumberPath<Integer> questionNum = createNumber("questionNum", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> questionUpdatedTime = createDateTime("questionUpdatedTime", java.time.LocalDateTime.class);

    public final ListPath<com.neighborhood.domain.todayquestion.entity.TodayQuestionAnswer, com.neighborhood.domain.todayquestion.entity.QTodayQuestionAnswer> todayQuestionAnswers = this.<com.neighborhood.domain.todayquestion.entity.TodayQuestionAnswer, com.neighborhood.domain.todayquestion.entity.QTodayQuestionAnswer>createList("todayQuestionAnswers", com.neighborhood.domain.todayquestion.entity.TodayQuestionAnswer.class, com.neighborhood.domain.todayquestion.entity.QTodayQuestionAnswer.class, PathInits.DIRECT2);

    public final EnumPath<com.neighborhood.domain.pretest.entity.TestType> todayQuestionType = createEnum("todayQuestionType", com.neighborhood.domain.pretest.entity.TestType.class);

    public QFamily(String variable) {
        super(Family.class, forVariable(variable));
    }

    public QFamily(Path<? extends Family> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFamily(PathMetadata metadata) {
        super(Family.class, metadata);
    }

}

