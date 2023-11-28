package com.neighborhood.domain.profile.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmergencyContact is a Querydsl query type for EmergencyContact
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmergencyContact extends EntityPathBase<EmergencyContact> {

    private static final long serialVersionUID = 1757623454L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmergencyContact emergencyContact = new QEmergencyContact("emergencyContact");

    public final NumberPath<Long> EmergencyContactId = createNumber("EmergencyContactId", Long.class);

    public final com.neighborhood.domain.member.entity.QMember member;

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public QEmergencyContact(String variable) {
        this(EmergencyContact.class, forVariable(variable), INITS);
    }

    public QEmergencyContact(Path<? extends EmergencyContact> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmergencyContact(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmergencyContact(PathMetadata metadata, PathInits inits) {
        this(EmergencyContact.class, metadata, inits);
    }

    public QEmergencyContact(Class<? extends EmergencyContact> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.neighborhood.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

