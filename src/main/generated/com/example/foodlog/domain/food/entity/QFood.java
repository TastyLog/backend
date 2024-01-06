package com.example.foodlog.domain.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFood is a Querydsl query type for Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = -1803852214L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFood food = new QFood("food");

    public final com.example.foodlog.global.common.entity.QBaseTimeEntity _super = new com.example.foodlog.global.common.entity.QBaseTimeEntity(this);

    public final StringPath address = createString("address");

    public final StringPath category = createString("category");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath latitude = createString("latitude");

    public final StringPath longtitude = createString("longtitude");

    public final StringPath naverLink = createString("naverLink");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath rating = createString("rating");

    public final StringPath reprsentativeImage = createString("reprsentativeImage");

    public final StringPath restaurant = createString("restaurant");

    public final NumberPath<Integer> totalReview = createNumber("totalReview", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath youtubeLink = createString("youtubeLink");

    public final QYoutuber youtuber;

    public QFood(String variable) {
        this(Food.class, forVariable(variable), INITS);
    }

    public QFood(Path<? extends Food> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFood(PathMetadata metadata, PathInits inits) {
        this(Food.class, metadata, inits);
    }

    public QFood(Class<? extends Food> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.youtuber = inits.isInitialized("youtuber") ? new QYoutuber(forProperty("youtuber")) : null;
    }

}

