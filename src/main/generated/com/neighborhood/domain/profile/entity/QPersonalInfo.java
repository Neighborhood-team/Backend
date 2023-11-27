package com.neighborhood.domain.profile.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersonalInfo is a Querydsl query type for PersonalInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonalInfo extends EntityPathBase<PersonalInfo> {

    private static final long serialVersionUID = -1609739811L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalInfo personalInfo = new QPersonalInfo("personalInfo");

    public final StringPath birthdayGift = createString("birthdayGift");

    public final StringPath bloodType = createString("bloodType");

    public final StringPath hates = createString("hates");

    public final StringPath interests = createString("interests");

    public final StringPath likes = createString("likes");

    public final StringPath mbti = createString("mbti");

    public final com.neighborhood.domain.member.entity.QMember member;

    public final StringPath motto = createString("motto");

    public final StringPath mySizes = createString("mySizes");

    public final NumberPath<Long> personalInfoId = createNumber("personalInfoId", Long.class);

    public final StringPath placeToTrip = createString("placeToTrip");

    public final StringPath roleInFam = createString("roleInFam");

    public QPersonalInfo(String variable) {
        this(PersonalInfo.class, forVariable(variable), INITS);
    }

    public QPersonalInfo(Path<? extends PersonalInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPersonalInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPersonalInfo(PathMetadata metadata, PathInits inits) {
        this(PersonalInfo.class, metadata, inits);
    }

    public QPersonalInfo(Class<? extends PersonalInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.neighborhood.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

