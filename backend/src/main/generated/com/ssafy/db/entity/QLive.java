package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLive is a Querydsl query type for Live
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLive{

    private static final long serialVersionUID = 846542478L;

    public static final QLive live = new QLive("live");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Integer> live_pk = createNumber("live_pk",Integer.class);

    public final NumberPath<Integer> product_pk = createNumber("product_pk",Integer.class);

    public final StringPath live_title = createString("live_title");

    public final NumberPath<Integer> live_viewer_count = createNumber("live_viewer_count",Integer.class);

    public QLive(String variable) {
        super(Live.class, forVariable(variable));
    }

    public QLive(Path<? extends Live> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLive(PathMetadata metadata) {
        super(Live.class, metadata);
    }

}