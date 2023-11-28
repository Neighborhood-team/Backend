package com.neighborhood.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -931867480L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final ListPath<com.neighborhood.domain.profile.entity.EmergencyContact, com.neighborhood.domain.profile.entity.QEmergencyContact> emergencyContacts = this.<com.neighborhood.domain.profile.entity.EmergencyContact, com.neighborhood.domain.profile.entity.QEmergencyContact>createList("emergencyContacts", com.neighborhood.domain.profile.entity.EmergencyContact.class, com.neighborhood.domain.profile.entity.QEmergencyContact.class, PathInits.DIRECT2);

    public final com.neighborhood.domain.family.entity.QFamily family;

    public final EnumPath<FamilyRole> familyRole = createEnum("familyRole", FamilyRole.class);

    public final StringPath fcmToken = createString("fcmToken");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final com.neighborhood.domain.profile.entity.QPersonalInfo personalInfo;

    public final StringPath phone = createString("phone");

    public final com.neighborhood.domain.pretest.entity.QResult result;

    public final ListPath<com.neighborhood.domain.profile.entity.Schedule, com.neighborhood.domain.profile.entity.QSchedule> schedules = this.<com.neighborhood.domain.profile.entity.Schedule, com.neighborhood.domain.profile.entity.QSchedule>createList("schedules", com.neighborhood.domain.profile.entity.Schedule.class, com.neighborhood.domain.profile.entity.QSchedule.class, PathInits.DIRECT2);

    public final ListPath<com.neighborhood.domain.todaymood.entity.TodayMood, com.neighborhood.domain.todaymood.entity.QTodayMood> todayMoods = this.<com.neighborhood.domain.todaymood.entity.TodayMood, com.neighborhood.domain.todaymood.entity.QTodayMood>createList("todayMoods", com.neighborhood.domain.todaymood.entity.TodayMood.class, com.neighborhood.domain.todaymood.entity.QTodayMood.class, PathInits.DIRECT2);

    public final ListPath<com.neighborhood.domain.todayquestion.entity.TodayQuestionAnswer, com.neighborhood.domain.todayquestion.entity.QTodayQuestionAnswer> todayQuestionAnswers = this.<com.neighborhood.domain.todayquestion.entity.TodayQuestionAnswer, com.neighborhood.domain.todayquestion.entity.QTodayQuestionAnswer>createList("todayQuestionAnswers", com.neighborhood.domain.todayquestion.entity.TodayQuestionAnswer.class, com.neighborhood.domain.todayquestion.entity.QTodayQuestionAnswer.class, PathInits.DIRECT2);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.family = inits.isInitialized("family") ? new com.neighborhood.domain.family.entity.QFamily(forProperty("family")) : null;
        this.personalInfo = inits.isInitialized("personalInfo") ? new com.neighborhood.domain.profile.entity.QPersonalInfo(forProperty("personalInfo"), inits.get("personalInfo")) : null;
        this.result = inits.isInitialized("result") ? new com.neighborhood.domain.pretest.entity.QResult(forProperty("result"), inits.get("result")) : null;
    }

}

