package com.neighborhood.domain.pretest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTypeImage is a Querydsl query type for TypeImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTypeImage extends EntityPathBase<TypeImage> {

    private static final long serialVersionUID = -81368250L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTypeImage typeImage = new QTypeImage("typeImage");

    public final NumberPath<Long> imageId = createNumber("imageId", Long.class);

    public final StringPath imageName = createString("imageName");

    public final StringPath imageUrl = createString("imageUrl");

    public final QResult result;

    public QTypeImage(String variable) {
        this(TypeImage.class, forVariable(variable), INITS);
    }

    public QTypeImage(Path<? extends TypeImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTypeImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTypeImage(PathMetadata metadata, PathInits inits) {
        this(TypeImage.class, metadata, inits);
    }

    public QTypeImage(Class<? extends TypeImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.result = inits.isInitialized("result") ? new QResult(forProperty("result"), inits.get("result")) : null;
    }

}

