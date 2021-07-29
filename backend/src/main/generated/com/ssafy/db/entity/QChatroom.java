package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatroom extends EntityPathBase<QChatroom> {

    private static final long serialVersionUID = 846542480L;

    public static final QChatroom chatroom = new QChatroom("chatroom");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> chat_room_pk = createNumber("chat_room_pk");

    public final NumberPath<Long> product_pk = createNumber("product_pk");

    public final StringPath user_id_buyer = createString("user_id_buyer");

    public final NumberPath<Long> chat_room_is_buyer = createNumber("chat_room_is_buyer");


    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

