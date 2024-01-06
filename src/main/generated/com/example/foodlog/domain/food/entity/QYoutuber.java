package com.example.foodlog.domain.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QYoutuber is a Querydsl query type for Youtuber
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QYoutuber extends EntityPathBase<Youtuber> {

    private static final long serialVersionUID = 1098300827L;

    public static final QYoutuber youtuber = new QYoutuber("youtuber");

    public final com.example.foodlog.global.common.entity.QBaseTimeEntity _super = new com.example.foodlog.global.common.entity.QBaseTimeEntity(this);

    public final StringPath channelId = createString("channelId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<Food, QFood> foodArrayList = this.<Food, QFood>createList("foodArrayList", Food.class, QFood.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath youtuberName = createString("youtuberName");

    public final StringPath youtuberProfile = createString("youtuberProfile");

    public QYoutuber(String variable) {
        super(Youtuber.class, forVariable(variable));
    }

    public QYoutuber(Path<? extends Youtuber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QYoutuber(PathMetadata metadata) {
        super(Youtuber.class, metadata);
    }

}

